package com.buzz.jwtdemo.service;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buzz.jwtdemo.common.JwtMessageKey;
import com.buzz.jwtdemo.common.MessageUtil;
import com.buzz.jwtdemo.common.ResponseConstants;
import com.buzz.jwtdemo.domain.dto.MemberPojo;
import com.buzz.jwtdemo.domain.dto.MemberPojo.MemberDto;
import com.buzz.jwtdemo.domain.dto.MemberPojo.MemberWithRoleDto;
import com.buzz.jwtdemo.domain.member.MemberEntity;
import com.buzz.jwtdemo.domain.member.MemberEntityRepository;
import com.buzz.jwtdemo.domain.memberrole.MemberRoleEntity;
import com.buzz.jwtdemo.domain.memberrole.MemberRoleEntityRepository;
import com.buzz.jwtdemo.enumerate.EnumMemberLevel;
import com.buzz.jwtdemo.enumerate.EnumRoleName;

@Service
@Transactional(readOnly = false)
public class UserService extends JwtBaseService{
	
	private static Logger _logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private MemberEntityRepository _memberEntityRepository;
	
	@Autowired
	private MemberRoleEntityRepository _memberRoleEntityRepository;
	
	@Transactional(readOnly = true)
	public HashMap<String,Object> allMember(){
		
		HashMap<String,Object> result = this.makeResultMap();
		
		/*LocalDateTime startDate = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime endDate = startDate.plusDays(1);
		List<MemberDomainDTO.MemberRoleDto> memberRoleList = _memberRoleEntityRepository.viewMemberRoleList(startDate,endDate);*/
		
		/*서비스 Layer에서 Entity -> DTO 변환 */
		List<MemberRoleEntity> memberRoleList = _memberRoleEntityRepository.findMemberRoleList();
		
		List<MemberWithRoleDto> memberRoleDtoList = new ArrayList<MemberWithRoleDto>();
		
		for(MemberRoleEntity memberRole : memberRoleList ) {
			MemberWithRoleDto memberWithRole =  new MemberWithRoleDto();
			memberWithRole.setMemberDto(new MemberDto());
			
			//MemberRoleEntity -> MemberRoleDTO 변환
			BeanUtils.copyProperties(memberRole, memberWithRole);
						
			//MemberEntity -> MemberDTO 변환
			BeanUtils.copyProperties(memberRole.getMember(), memberWithRole.getMemberDto() );
									
			memberRoleDtoList.add(memberWithRole);
		}
				
		result.put(ResponseConstants.DATA, memberRoleDtoList);
				
		return result;		
	}
	
	/*************************************************************************************************************
	 * Repository 에서 Entity -> DTO를 변환하는 Case
	 *************************************************************************************************************/
	@Transactional(readOnly = true)
	public HashMap<String,Object> periodMember(){
		
		HashMap<String,Object> result = this.makeResultMap();
		
		LocalDateTime startDate = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime endDate = startDate.plusDays(1);
		List<MemberPojo.MemberWithRoleDto> memberRoleList = _memberRoleEntityRepository.viewMemberRoleList(startDate,endDate);
						
		result.put(ResponseConstants.DATA, memberRoleList);
				
		return result;		
	}
	
	public HashMap<String,Object> info(){
		HashMap<String,Object> result = this.makeResultMap();
		
		//response flag 설정
		result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
		result.put(ResponseConstants.MESSAGE,MessageUtil.getMessage(JwtMessageKey.MSG_KEY_OK ) );
		
		return result;
	}

	//신규유저 추가	
	public HashMap<String,Object> addUser(HashMap<String,Object> newUser){
		HashMap<String,Object> result = this.makeResultMap();
		
		String loginId = newUser.get("loginId").toString();
		String loginPass = newUser.get("loginPass").toString();
		
		MemberEntity member = MemberEntity.builder()
				.loginId(loginId)
				.loginPass(loginPass)
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
			
		
		return result;
	}
	
	public HashMap<String,Object> updateUser(HashMap<String,Object> user){
		HashMap<String,Object> result = this.makeResultMap();
		
		_logger.debug("update user : {} ",user.get("userId").toString());
		
		//response flag 설정
		result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
		result.put(ResponseConstants.MESSAGE,MessageUtil.getMessage(JwtMessageKey.MSG_KEY_OK ) );
		
		return result;
	}
	
	public HashMap<String,Object> deleteUser(HashMap<String,Object> user){
		HashMap<String,Object> result = this.makeResultMap();
		
		_logger.debug("delete user : {} ",user.get("userId").toString());
		
		//response flag 설정
		result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
		result.put(ResponseConstants.MESSAGE,MessageUtil.getMessage(JwtMessageKey.MSG_KEY_OK ) );
		
		return result;
	}
	
}
