package com.buzz.jwtdemo.domain.member;

import java.util.List;

/*******************************************************************************************************
 * QueryDSL 에서 구현할 메소드 정의
 *******************************************************************************************************/
public interface CustomMemberEntityRepository {
	
	List<MemberEntity> findActiveMember();
}
