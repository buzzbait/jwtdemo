package com.buzz.jwtdemo.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buzz.jwtdemo.model.JwtUserDetail;
import com.buzz.jwtdemo.repository.UserRepo;
import com.buzz.jwtdemo.security.JwtTokenProvider;

@Service
public class AuthService {
	
	private static Logger logger = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider; 
	
	@Autowired
	private UserRepo userRepo;
	
	public HashMap<String,Object> signin(String userId,String userPass){
		HashMap<String,Object> result =  new HashMap<String,Object>();
		
		JwtUserDetail findUser = userRepo.findbyId(userId);
		//사용자 검증 진행
		//사용자가 인증되면 인증키를 발급한다.
		
		String tokenValue = this.jwtTokenProvider.createToken(findUser.getUsername(),findUser.getRoles());
		
		result.put("status_code", 0);
		result.put("tokenValue", tokenValue);
		
		logger.debug("토큰 : {}",tokenValue);
		
		return result;
	}
}
