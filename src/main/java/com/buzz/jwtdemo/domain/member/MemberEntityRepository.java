package com.buzz.jwtdemo.domain.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**********************************************************************************************************
 * JpaRepository 사용시 @Repository 어노테이션은 필요 없음
 **********************************************************************************************************/
public interface MemberEntityRepository extends JpaRepository<MemberEntity,Long> ,CustomMemberEntityRepository{
	
	//로그인ID로 검색
	public Optional<MemberEntity> findByLoginId(String loginId);
}