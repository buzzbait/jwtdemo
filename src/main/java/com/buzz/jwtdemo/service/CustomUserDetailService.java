package com.buzz.jwtdemo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.buzz.jwtdemo.model.JwtUserDetail;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String userPk) {
		JwtUserDetail userDetail =  new JwtUserDetail();		
        return userDetail;
    }
}
