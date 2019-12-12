package com.buzz.jwtdemo.repository;

import org.springframework.stereotype.Repository;

import com.buzz.jwtdemo.model.JwtUserDetail;

@Repository
public class UserRepo {
	
	public JwtUserDetail findbyId(String userKey) {
		
		JwtUserDetail jwtUserDetail =  new JwtUserDetail();
		
		jwtUserDetail.setUsername(userKey);
		jwtUserDetail.setPassword("userpass");
		
		jwtUserDetail.getRoles().add("ROLE_USER");
		//jwtUserDetail.getRoles().add("ROLE_ADMIN");
				
		return jwtUserDetail;
	}
}
