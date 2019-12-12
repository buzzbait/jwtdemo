package com.buzz.jwtdemo.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping(value = "/info")
    public HashMap<String,Object> userinfo(@RequestParam String id) {
    	
		HashMap<String,Object> result =  new HashMap<String,Object>();
		
		result.put("status_code", "0");
		result.put("userName", id +  " : loginName");
		
    	return result;        
    }
}
