package com.buzz.jwtdemo.domain.member;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.buzz.jwtdemo.enumerate.ActiveStatus;

/*******************************************************************************************************
 * QueryDSL 를 이용해서 쿼리 구현
 *******************************************************************************************************/
public class CustomMemberEntityRepositoryImpl extends QuerydslRepositorySupport implements CustomMemberEntityRepository  {

	//QuerydslRepositorySupport 클래스에는 기본생성자가 없음.
    public CustomMemberEntityRepositoryImpl() {
        super(MemberEntity.class);
    }
    
    @Override
    public List<MemberEntity> findActiveMember(){
    	final QMemberEntity member = QMemberEntity.memberEntity;
   	
    	return from(member)
    			.where(member.activeStatus.eq(ActiveStatus.ON))
    			.fetch();    	
    	
    }
}
