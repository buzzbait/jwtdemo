package com.buzz.jwtdemo.domain.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberEntityRepository extends JpaRepository<MemberEntity,Long> ,CustomMemberEntityRepository{
	
	//로그인ID로 검색
	public Optional<MemberEntity> findByLoginId(String loginId);
}