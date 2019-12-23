package com.buzz.jwtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buzz.jwtdemo.model.MemberRole;
import com.buzz.jwtdemo.model.Member;
import java.util.List;
import java.lang.Long;

public interface  MemberRoleRepository extends JpaRepository<MemberRole,Long> {

	public List<MemberRole> findByMember(Member member); 
	public List<MemberRole> findByMemberLoginId(String loginId);
	public List<MemberRole> findByMemberId(Long Id); 
		
}
