package com.buzz.jwtdemo.domain.memberrole;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.buzz.jwtdemo.domain.dto.MemberPojo;
import com.buzz.jwtdemo.domain.dto.MemberPojo.MemberDto;
import com.buzz.jwtdemo.domain.dto.MemberPojo.MemberWithRoleDto;
import com.buzz.jwtdemo.domain.member.MemberEntity;
import com.buzz.jwtdemo.domain.member.MemberEntityRepository;
import com.buzz.jwtdemo.enumerate.EnumMemberLevel;
import com.buzz.jwtdemo.enumerate.EnumRoleName;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@Transactional

class MemberRoleEntityRepositoryTest {

	private static Logger logger = LoggerFactory.getLogger(MemberRoleEntityRepositoryTest.class);
	
	@Autowired
	private MemberEntityRepository _memberEntityRepository;
	
	@Autowired
	private MemberRoleEntityRepository _memberRoleEntityRepository;
	
	
	@Disabled
	@Rollback(false)
	@Test
	void test_addNewMember() {
		MemberEntity member = MemberEntity.builder()
				.loginId("testran")
				.loginPass("passss")
				.level(EnumMemberLevel.MANAGER)
				.build();
		
		_memberEntityRepository.save(member);
		
		MemberRoleEntity memberRole1 = MemberRoleEntity.builder()
				.member(member)
				.role(EnumRoleName.ADMIN)
				.build();
		
		MemberRoleEntity memberRole2 = MemberRoleEntity.builder()
				.member(member)
				.role(EnumRoleName.USER)
				.build();
		
		_memberRoleEntityRepository.save(memberRole1);
		_memberRoleEntityRepository.save(memberRole2);
		
		List<MemberRoleEntity> memberRoleList = _memberRoleEntityRepository.findByMember(member);
		assertEquals(2, memberRoleList.size());	
		
		//fail("Not yet implemented");
	}
	
	@Disabled
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

	@Disabled
	@Rollback(false)
	@Test
	void test_updateMember() {
		List<MemberRoleEntity> memberRoleList = _memberRoleEntityRepository.findByMemberLoginId("test1111");
		MemberRoleEntity memberRoleEntity =  memberRoleList.get(0);
		MemberEntity memberEntity = memberRoleEntity.getMember();
		
		memberEntity.setLoginPass("updateesss");
	}
	
	//@Disabled
	@Test
	void test_findAll() {
						
		List<MemberRoleEntity> memberRoleList = _memberRoleEntityRepository.findMemberRoleList();
		
		List<MemberWithRoleDto> memberWithRoleList = new ArrayList<MemberWithRoleDto>();
		
		for(MemberRoleEntity memberRole : memberRoleList ) {
			MemberWithRoleDto memberWithRole =  new MemberWithRoleDto();
			memberWithRole.setMemberDto(new MemberDto());
			BeanUtils.copyProperties(memberRole, memberWithRole);
						
			BeanUtils.copyProperties(memberRole.getMember(), memberWithRole.getMemberDto() );
									
			memberWithRoleList.add(memberWithRole);
		}
		
		for(MemberWithRoleDto memberRole : memberWithRoleList) {
			logger.debug("LOGIN ID : {} - ROLE NAME : {}", memberRole.getMemberDto().getLoginId() ,memberRole.getRoleName().toString());
		}
		
		//assertEquals(7, memberRoleList.size());
	}
	
	@Disabled
	@Test
	void test_findQueryDsl() {
		
		//당일등록
		LocalDateTime startDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime endDateTime = startDateTime.plusDays(1);
		
		List<MemberPojo.MemberWithRoleDto> memberRoleList = _memberRoleEntityRepository.viewMemberRoleList(startDateTime,endDateTime);
				
				
		for(MemberPojo.MemberWithRoleDto memberRole : memberRoleList) {
			logger.debug("LOGIN ID : {} - ROLE NAME : {}", memberRole.getMemberDto().getLoginId(),memberRole.getRoleName());
		}
		
		assertEquals(7, memberRoleList.size());
	}
}
