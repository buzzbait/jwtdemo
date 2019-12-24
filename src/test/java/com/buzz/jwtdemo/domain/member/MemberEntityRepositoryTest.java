package com.buzz.jwtdemo.domain.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class MemberEntityRepositoryTest {
	
	@Autowired
	private MemberEntityRepository _memberEntityRepository;

	@Test
	void test_findByActiveMember() {
		
		List<MemberEntity> memberEntityList = _memberEntityRepository.findActiveMember();
		
		assertEquals(4, memberEntityList.size());		

	}
}
