package com.buzz.jwtdemo.domain.memberrole;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buzz.jwtdemo.domain.member.MemberEntity;


public interface MemberRoleEntityRepository extends JpaRepository<MemberRoleEntity,Long>,CustomMemberRoleEntityRepository{
	public List<MemberRoleEntity> findByMember(MemberEntity member); 
	public List<MemberRoleEntity> findByMemberLoginId(String loginId);
	public List<MemberRoleEntity> findByMemberId(Long Id); 
}
