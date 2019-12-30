package com.buzz.jwtdemo.domain.memberrole;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.buzz.jwtdemo.domain.dto.MemberDtoDomain;
import com.buzz.jwtdemo.domain.member.QMemberEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;

/*********************************************************************************************************************
 * Entity -> DTO 변환을 Repository Layer 에서 할 것인지 , Servicce Layer 에서 할 것인지 결정
 *********************************************************************************************************************/
public class CustomMemberRoleEntityRepositoryImpl extends QuerydslRepositorySupport implements CustomMemberRoleEntityRepository{

	//QuerydslRepositorySupport 클래스에는 기본생성자가 없음.
    public CustomMemberRoleEntityRepositoryImpl() {
        super(MemberRoleEntity.class);
    }
    
    @Override
	public List<MemberRoleEntity> findMemberRoleList(){
		 
    	final QMemberEntity member = QMemberEntity.memberEntity;
    	final QMemberRoleEntity memberRole = QMemberRoleEntity.memberRoleEntity;
		 
    	return from(memberRole)
    			.innerJoin(memberRole.member, member)
				.fetch();		 
		 
	 }
    
    //Entity  가 아닌 DTO 에 매핑하여 반환
    @Override
	public List<MemberDtoDomain.MemberWithRoleDto> viewMemberRoleList(LocalDateTime startDate,LocalDateTime endDate){
		 
    	final QMemberEntity member = QMemberEntity.memberEntity;
    	final QMemberRoleEntity memberRole = QMemberRoleEntity.memberRoleEntity;
    	
    	/**********************************************************************************************
    	 * 날짜검색시 유의사항 ex)금일 등록건수
    		LocalDateTime startDate = dateSearch.truncatedTo(ChronoUnit.DAYS); //시작일
    		LocalDateTime endDate = startDate.plusDays(1);	//+1 하여 다음날자
    		
    		startDate = 2019.12.11 00.00.00
    		endDate =   2019.12.12 00.00.00
    		
    		between 을 사용하게 되면 endDate 가 포함되어 실제 2019.12.12 00.00.00 에 등록된것이 있으면
    		다음날 등록된 건도 포함된다.
    		
    		goe,lt 를 사용 하여 확실하게 당일 등록 만 검색
    		goe : greater or equal ( >= )
    		lt :  <
    		
    		JPASubQuery 는 과거버전이고 최신버전에서는 JPAExpressions 을 사용 한다
    	**********************************************************************************************/
    	
    	BooleanBuilder builder = new BooleanBuilder();
    	//builder.and(memberRole.member.crtDtm.between(startDate, endDate));
    	builder.and(memberRole.member.crtDtm.goe(startDate).and(memberRole.member.crtDtm.lt(endDate)));
    	
    	/*Where 절의 SubQuery는 JPAExpressions 를 이용 한다 
    	 * builder.and(academy.id.in(
                 JPAExpressions
                         .select(student.academy.id)
                         .from(student)
                         .where(student.id.eq(studentId))))
    	 */
    	
    	return from(memberRole)
    			.select(
    					Projections.bean(MemberDtoDomain.MemberWithRoleDto.class
    							,memberRole.id
    							,memberRole.roleName
    							, Projections.bean(MemberDtoDomain.MemberDto.class
    										,memberRole.member.id
    										,memberRole.member.loginId
    	                                    ,memberRole.member.loginPass
    	                                    ,memberRole.member.activeStatus
    	                                    ,memberRole.member.level
    	                                    ,memberRole.member.updDtm
    	                                    ,memberRole.member.crtDtm
    	                            ).as("memberDto")    							
    							,memberRole.updDtm
    							,memberRole.crtDtm    						
    							
    							/* SELECT 절의 SubQuery 는 아래와 같이 구현 
    							 * ,ExpressionUtils.as(
    		                                JPAExpressions.select(count(student.id))
    		                                        .from(student)
    		                                        .where(student.academy.eq(academy)),
    		                                "studentCount")*/
    							
    							))
    			.innerJoin(memberRole.member, member)
    			.where(builder)
				.fetch();		 
		 
	 }
}
