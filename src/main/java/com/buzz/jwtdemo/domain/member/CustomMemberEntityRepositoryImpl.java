package com.buzz.jwtdemo.domain.member;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.buzz.jwtdemo.domain.dto.MemberDomainDTO;
import com.buzz.jwtdemo.domain.dto.MemberDomainDTO.MemberDto;
import com.buzz.jwtdemo.enumerate.EnumActiveStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

/*******************************************************************************************************
 * QueryDSL 를 이용해서 쿼리 구현
 * BooleanBuilder 를 사용해서 Dynamic 조건 구성
 *******************************************************************************************************/
public class CustomMemberEntityRepositoryImpl extends QuerydslRepositorySupport implements CustomMemberEntityRepository  {

	//QuerydslRepositorySupport 클래스에는 기본생성자가 없음.
    public CustomMemberEntityRepositoryImpl() {
        super(MemberEntity.class);
    }
        
    @Override
    public List<MemberEntity> findActiveMember(){
    	final QMemberEntity member = QMemberEntity.memberEntity;
   	
    	/* BooleanBuilder 사용예시
    	BooleanBuilder builder = new BooleanBuilder();
    	builder.and(member.activeStatus.eq(ActiveStatus.ON));
    	    	
    	return from(member)
    			.where(builder)
    			.fetch();    	
    	*/
    	
    	return from(member)
    			.where(_isActiveUser(member))
    			.fetch();  
    }
    
    @Override
    public List<MemberDto> findAllMember(){
    	final QMemberEntity member = QMemberEntity.memberEntity;
    	return from(member)
    			.select(Projections.bean(MemberDomainDTO.MemberDto.class
    					,member.id
    					,member.loginId
    					,member.loginPass
    					,member.activeStatus    					
    					)
    			)
    			.fetch();  
    }
    
    //중복으로 사용되는 조건은 extract method 를 통해서 재사용한다
    private BooleanExpression _isActiveUser(QMemberEntity member) {
    	return member.activeStatus.eq(EnumActiveStatus.ON);
    }
}
