package com.buzz.jwtdemo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.buzz.jwtdemo.model.Member;
import com.buzz.jwtdemo.model.MemberRole;
import com.buzz.jwtdemo.model.Role;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class MemberRoleRepositoryTest {

	private static Logger _logger = LoggerFactory.getLogger(MemberRoleRepositoryTest.class);
	
	@Autowired
	private MemberRepository _memberRepository;
	
	@Autowired
	private RoleRepository _roleRepository;
	
	@Autowired
	private MemberRoleRepository _memberRoleRepository;

	//@Disabled
	@Test
	void test_findByMember() {
		
		//Member member = Member.builder().loginId("test").build();
		List<MemberRole> memberRoles =  _memberRoleRepository.findByMemberId(1L);		
		//List<MemberRole> memberRoles =  _memberRoleRepository.findByMemberLoginId("demo");
		
		assertEquals(2, memberRoles.size());
		
		//Optional<MemberRole> optMemberRoles =  _memberRoleRepository.findById(Long.valueOf(1));		
		//assertEquals(true, optMemberRoles.isPresent());
	}

	@Disabled
	@Rollback(false)	
	@Test
	void test_addNewMember() {
		//롤 검색
		Optional<Role> optRole1 = _roleRepository.findById(Long.valueOf(1));
		if(!optRole1.isPresent()) {
			fail("ROLE NOT FOUND...");		
		}
		
		//ENTITY 정의
		Optional<Role> optRole2 = _roleRepository.findById(Long.valueOf(2));		
		Member newMember = Member.builder().loginId("test1122").loginPass("test1111").build();		
				
		MemberRole newMemberRole1 =  MemberRole.builder().member(newMember).role(optRole1.get()).build();					
		MemberRole newMemberRole2 =  MemberRole.builder().member(newMember).role(optRole2.get()).build();		
		
		//INSERT.......
		_memberRepository.save(newMember);
		_memberRoleRepository.save(newMemberRole1);
		_memberRoleRepository.save(newMemberRole2);
		
		assertEquals("test1111", newMemberRole1.getMember().getLoginId());
		
	}
	
	@Disabled
	//@Rollback(false)
	@Test
	void test_removeMember() {
		Optional<Member> optMember = _memberRepository.findByLoginId("test");
		
		if(!optMember.isPresent()) {
			fail("MEMBER NOT FOUND...");		
		}
		
		//단방향 설정이기 때문에 memberRole 먼저 지우고 , 후에 member 를 지운다
		//cascade 를 적용하기 위해서는 양방향 으로 설정이 되어야 한다.
		//Member 에는 MemberRole 참조가 없기 때문에 cascade 가 적용이 안된다 
		Member member =  optMember.get();		
		
		//1. memberRole 삭제
		List<MemberRole> memberRoleList =  _memberRoleRepository.findByMember(member);		
		_memberRoleRepository.deleteInBatch(memberRoleList);
		
		//2. member 삭제
		_memberRepository.delete(member);
		
		
				
	}
	
	
}
