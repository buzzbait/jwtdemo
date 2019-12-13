package com.buzz.jwtdemo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buzz.jwtdemo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService _userService;
	
	@GetMapping(value = "/info")
    public HashMap<String,Object> userInfo(@RequestParam String id) {
    	
		HashMap<String,Object> result =  _userService.info();		
    	return result;        
    }
		
	/*
	 * @Secured는 표현식 사용할 수 없고,@PreAuthroize는 표현식 사용 가능
	 * @EnableGlobalMethodSecurity(prePostEnabled = true) 를 설정해여 작동 한다
	 * @PreAuthroize 에서 발생되는 오류는 ControllerAdvice 에서 처리 한다
	 */	
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/secure")
    public HashMap<String,Object> userSecure(@RequestParam String id) {
    	
		HashMap<String,Object> result =  new HashMap<String,Object>();
		
		result.put("status_code", "0");
		result.put("desc", "secure method...");
		
    	return result;        
    }
}
