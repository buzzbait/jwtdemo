package com.buzz.jwtdemo.exception;

import java.util.HashMap;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtControllerAdvice {
	 
	//@PreAuthorize 사용된 컨트롤러중 권한이 없어서 발생하는 오류 Catch........
	@ExceptionHandler(AccessDeniedException.class) 
	public HashMap<String,Object> accessDeniedException() {
		HashMap<String,Object> result = new HashMap<String,Object>();
	        
	    result.put("status_code", -1);
	    result.put("error_msg","접근 권한 오류 입니다");        
	    return result;
	}	
	
	//일반적인 오류 Catch........
	@ExceptionHandler(Exception.class) 
	public HashMap<String,Object> customException() {
		HashMap<String,Object> result = new HashMap<String,Object>();
	        
	    result.put("status_code", -1);
	    result.put("error_msg","Controller 내부 오류 입니다");        
	    return result;
	}
	
	 
}
