package com.buzz.jwtdemo.domain.memberrole;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.buzz.jwtdemo.domain.dto.MemberDomainDTO;
import com.buzz.jwtdemo.domain.member.QMemberEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;


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
	public List<MemberDomainDTO.MemberRoleDto> viewMemberRoleList(LocalDateTime startDate,LocalDateTime endDate){
		 
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
    	**********************************************************************************************/
    	
    	BooleanBuilder builder = new BooleanBuilder();
    	//builder.and(memberRole.member.crtDtm.between(startDate, endDate));
    	builder.and(memberRole.member.crtDtm.goe(startDate).and(memberRole.member.crtDtm.lt(endDate)));
    	    	 
    	return from(memberRole)
    			.select(
    					Projections.bean(MemberDomainDTO.MemberRoleDto.class
    							,memberRole.id
    							,memberRole.roleName
    							,memberRole.member.id.as("memberId")
    							,memberRole.member.loginId
    							,memberRole.member.loginPass
    							,memberRole.member.activeStatus
    							,memberRole.member.level
    							,memberRole.updDtm
    							,memberRole.crtDtm
    							,memberRole.member
    							))
    			.innerJoin(memberRole.member, member)
    			.where(builder)
				.fetch();		 
		 
	 }
}
