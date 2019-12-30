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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;


import com.buzz.jwtdemo.domain.dto.MemberDtoDomain.MemberDto;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@Transactional

class MemberEntityRepositoryTest {
	
	private static Logger logger = LoggerFactory.getLogger(MemberEntityRepositoryTest.class);
	
	@Autowired
	private MemberEntityRepository _memberEntityRepository;

	@Disabled
	@Test
	void test_findByActiveMember() {
		
		List<MemberEntity> memberEntityList = _memberEntityRepository.findActiveMember();
		
		assertEquals(4, memberEntityList.size());		

	}
	
	@Test
	void test_findAllMember() {
		
		List<MemberDto> memberDtoList = _memberEntityRepository.findAllMember();
		
				
		
		for(MemberDto member : memberDtoList) {			
			member.setActiveStatusName( member.getActiveStatus().getTitle());
		}
		
		for(MemberDto member : memberDtoList) {
			logger.debug("CODE:{} - TITLE:{}" , member.getActiveStatus().getCode(), member.getActiveStatusName());
		}
				
		assertEquals(4, memberDtoList.size());		

	}

	
	
	@Disabled
	@Rollback(false)	
	@Test
	void test_updateMember() {
		
		Optional<MemberEntity> optMember = _memberEntityRepository.findById(1L);
		
		if(optMember.isPresent()) {
			MemberEntity member = optMember.get();
			member.setLoginPass("ddd126");
			//member.setUpdDtm(LocalDateTime.now());
		}else {
			fail("member not found.......");
		}
		
		//assertEquals(4, memberEntityList.size());		

	}
}
