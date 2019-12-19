package com.buzz.jwtdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.buzz.jwtdemo.model.Member;


public interface MemberRepository extends JpaRepository<Member,Long> {
	
	//로그인ID로 검색
	public Optional<Member> findByLoginId(String loginId);
}
