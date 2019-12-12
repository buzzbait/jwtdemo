package com.buzz.jwtdemo.exception;

import java.util.HashMap;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtControllerAdvice {
	 
	@ExceptionHandler(Exception.class) 
	public HashMap<String,Object> entrypointException() {
		HashMap<String,Object> result = new HashMap<String,Object>();
	        
	    result.put("status_code", -1);
	    result.put("error_msg","Controller 내부 오류 입니다");        
	    return result;
	}
}
