package com.buzz.jwtdemo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buzz.jwtdemo.common.ResponseConstants;
import com.buzz.jwtdemo.model.JwtUserDetail;
import com.buzz.jwtdemo.model.Member;
import com.buzz.jwtdemo.model.MemberRole;
import com.buzz.jwtdemo.model.Role;
import com.buzz.jwtdemo.repository.MemberRepository;
import com.buzz.jwtdemo.repository.UserRepo;
import com.buzz.jwtdemo.security.JwtTokenProvider;


@Service
public class AuthService {
	
	private static Logger _logger = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	private JwtTokenProvider _jwtTokenProvider; 
	
	@Autowired
	private UserRepo _userRepo;
	
	@Autowired
	private MemberRepository _memberRepository;
	
	public HashMap<String,Object> signin(String userId,String userPass){
		HashMap<String,Object> result =  new HashMap<String,Object>();
		
		JwtUserDetail findUser = _userRepo.findbyId(userId);
		//사용자 검증 진행
		//사용자가 인증되면 인증키를 발급한다.
				
		//DB검색
		Optional<Member> optionalMember = _memberRepository.findByLoginId(userId);
		if(optionalMember.isPresent()) {
			//멤버가 존재 하는 경우
			Member loinMemeber = optionalMember.get();
			
			String loginPass = loinMemeber.getLoginPass();
			
			_logger.debug("LOGIN PASS : {}",loginPass);
			
			List<MemberRole > memberRoles = loinMemeber.getMemberRoles();			
			
			memberRoles.forEach((role) -> {
				Role userRole = role.getRole();
				_logger.debug("HAS ROLE : {}-{}",userRole.getRoleName(),userRole.getRoleDesc()  );
			});
			
			
			String tokenValue = this._jwtTokenProvider.createToken(findUser.getUsername(),findUser.getRoles());
			result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
			result.put("tokenValue", tokenValue);
		}else {
			//멤버가 없는 경우
			result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_ERROR);
			result.put(ResponseConstants.MESSAGE, "사용자 정보가 없습니다.");
		}
				
		
		return result;
	}
}
