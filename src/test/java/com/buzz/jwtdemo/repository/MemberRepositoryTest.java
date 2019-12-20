package com.buzz.jwtdemo.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.buzz.jwtdemo.model.Member;
import com.buzz.jwtdemo.model.MemberRole;
import com.buzz.jwtdemo.model.Role;

/******************************************************************************************************************
 * @DataJpaTest를 사용할 경우에 TestEntityManager 클래스가 자동으로 빈으로 등록
 * @AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE) 를 사용하면 메모리DB가 아닌
 * 실제 DB를 사용 한다
 * ※ run configuration 에서 junit버전을 맞춘다(junit4)
 ******************************************************************************************************************/

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class MemberRepositoryTest {

	@Autowired
	private MemberRepository _memberRepository;
	
	@Autowired
	private RoleRepository _roleRepository;
	
	@Autowired
	private MemberRoleRepository _memberRoleRepository;
	
	@Ignore
	@Test
	public void test_findByLoginId() {
		Optional<Member> optionalMember = _memberRepository.findByLoginId("demo");
		if(optionalMember.isPresent()) {
			//assertThat(optionalMember.get().getLoginId(), is("demo"));			
		}else {
			
		}
	}
	
	@Rollback(false)
	@Test
	public void test_addMember() {
		
		Optional<Role> optRole = _roleRepository.findById(1L);
		
		if( optRole.isPresent() ) {
			Role role =  optRole.get();
			MemberRole memberRole =  MemberRole.builder().role(role).build();
			List<MemberRole> memberRoles =  new ArrayList<MemberRole>();
			memberRoles.add(memberRole);
						
			Member member = Member.builder().loginId("demo111").loginPass("demo11111").memberRoles(memberRoles).build();			
			_memberRepository.save(member);
			
			_memberRoleRepository.save(memberRole);
			
		}else {
			
		}		
		
	}

}
