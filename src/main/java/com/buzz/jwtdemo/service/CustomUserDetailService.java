package com.buzz.jwtdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.buzz.jwtdemo.model.JwtUserDetail;
import com.buzz.jwtdemo.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userPk) {
		JwtUserDetail userDetail =  userRepo.findbyId(userPk);		
        return userDetail;
    }
}
