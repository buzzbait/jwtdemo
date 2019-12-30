package com.buzz.jwtdemo.domain.member;

import java.util.List;

import com.buzz.jwtdemo.domain.dto.MemberDtoDomain.MemberDto;

/*******************************************************************************************************
 * QueryDSL 에서 구현할 메소드 정의
 *******************************************************************************************************/
public interface CustomMemberEntityRepository {
	
	List<MemberEntity> findActiveMember();	
	List<MemberDto> findAllMember();
	
}
