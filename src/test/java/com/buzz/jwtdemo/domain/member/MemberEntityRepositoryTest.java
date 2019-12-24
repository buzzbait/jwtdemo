package com.buzz.jwtdemo.domain.member;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class MemberEntityRepositoryTest {
	
	@Autowired
	private MemberEntityRepository _memberEntityRepository;

	@Disabled
	@Test
	void test_findByActiveMember() {
		
		List<MemberEntity> memberEntityList = _memberEntityRepository.findActiveMember();
		
		assertEquals(4, memberEntityList.size());		

	}
	
	@Rollback(false)	
	@Test
	void test_updateMember() {
		
		Optional<MemberEntity> optMember = _memberEntityRepository.findById(1L);
		
		if(optMember.isPresent()) {
			MemberEntity member = optMember.get();
			member.setLoginPass("ddd121");
			member.setUpdDtm(LocalDateTime.now());
		}else {
			fail("member not found.......");
		}
		
		//assertEquals(4, memberEntityList.size());		

	}
}
