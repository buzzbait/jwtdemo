package com.buzz.jwtdemo.domain.memberrole;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.buzz.jwtdemo.domain.member.MemberEntity;
import com.buzz.jwtdemo.domain.member.MemberEntityRepository;
import com.buzz.jwtdemo.enumerate.MemberLevel;
import com.buzz.jwtdemo.enumerate.RoleName;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@Transactional

class MemberRoleEntityRepositoryTest {

	@Autowired
	private MemberEntityRepository _memberEntityRepository;
	
	@Autowired
	private MemberRoleEntityRepository _memberRoleEntityRepository;
	
	@Disabled
	@Rollback(false)
	@Test
	void test_addNewMember() {
		MemberEntity member = MemberEntity.builder()
				.loginId("test00001")
				.loginPass("passss")
				.level(MemberLevel.MANAGER)
				.build();
		
		_memberEntityRepository.save(member);
		
		MemberRoleEntity memberRole1 = MemberRoleEntity.builder()
				.member(member)
				.role(RoleName.ADMIN)
				.build();
		
		MemberRoleEntity memberRole2 = MemberRoleEntity.builder()
				.member(member)
				.role(RoleName.USER)
				.build();
		
		_memberRoleEntityRepository.save(memberRole1);
		_memberRoleEntityRepository.save(memberRole2);
		
		List<MemberRoleEntity> memberRoleList = _memberRoleEntityRepository.findByMember(member);
		assertEquals(2, memberRoleList.size());	
		
		//fail("Not yet implemented");
	}
	
	@Rollback(false)
	@Test
	void test_removeMember() {
		
		Optional<MemberEntity> optMemberEntity =  _memberEntityRepository.findByLoginId("test00001");
		
		if(optMemberEntity.isPresent()) {
			MemberEntity member = optMemberEntity.get();		
			List<MemberRoleEntity> memberRoleList = _memberRoleEntityRepository.findByMember(member);
			
			_memberRoleEntityRepository.deleteInBatch(memberRoleList);
			_memberEntityRepository.delete(member);
			
			//memberRoleList = _memberRoleEntityRepository.findByMember(member);
			//assertEquals(0, memberRoleList.size());	
		}else {
			fail("NOT FOUND MEMEBER....");
		}		
		
	}

}
