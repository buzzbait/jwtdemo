package com.buzz.jwtdemo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buzz.jwtdemo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	//http://localhost:8080/auth/signin?id=ddddd&password=dddddd
    @GetMapping(value = "/signin")
    public HashMap<String,Object> signin(@RequestParam String id,@RequestParam String password) {
    	
    	return authService.signin(id, password);        
    }
}
