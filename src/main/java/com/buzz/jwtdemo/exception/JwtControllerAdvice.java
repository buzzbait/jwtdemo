package com.buzz.jwtdemo.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.buzz.jwtdemo.common.JwtMessageKey;
import com.buzz.jwtdemo.common.MessageUtil;
import com.buzz.jwtdemo.common.ResponseConstants;


@RestControllerAdvice
public class JwtControllerAdvice {

	/**********************************************************************************************
	 * @PreAuthorize 어노테이션이 사용된 컨트롤러중 권한이 없어서 발생하는 오류 Catch....... 
	 **********************************************************************************************/
	@ExceptionHandler(AccessDeniedException.class) 
	public ResponseEntity<HashMap<String,Object>> accessDeniedException() {
		HashMap<String,Object> result = new HashMap<String,Object>();
	        
		result.put(ResponseConstants.STATUS , ResponseConstants.RESULT_NOTROLE);
	    result.put(ResponseConstants.MESSAGE, MessageUtil.getMessage(JwtMessageKey.MSG_KEY_FORBIDDEN));
	            
	    return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.FORBIDDEN);
	}	
	
	/**********************************************************************************************
	 * 404 Error Catch
	 * 404 Error 를 Catch 하기 위해서는 환경파일에 아래와 같은 설정이 추가되어야 함
	 * spring.mvc.throw-exception-if-no-handler-found=true
	 * spring.resources.add-mappings=false	 
	 **********************************************************************************************/
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<HashMap<String,Object>>  noHandlerFoundException() {
		HashMap<String,Object> result = new HashMap<String,Object>();
	        
		result.put(ResponseConstants.STATUS , ResponseConstants.RESULT_NOTFOUND);
	    result.put(ResponseConstants.MESSAGE, MessageUtil.getMessage(JwtMessageKey.MSG_KEY_NOTFOUND));
	            
	    return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.NOT_FOUND);
	}
	
	/**********************************************************************************************
	 * 일반적인 오류	 
	 **********************************************************************************************/
	@ExceptionHandler(Exception.class)
	public ResponseEntity<HashMap<String,Object>> normalException() {
		HashMap<String,Object> result = new HashMap<String,Object>();
	        
		result.put(ResponseConstants.STATUS , ResponseConstants.RESULT_ERROR);
	    result.put(ResponseConstants.MESSAGE, MessageUtil.getMessage(JwtMessageKey.MSG_KEY_ERROR));
	            
	    return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	 
}
