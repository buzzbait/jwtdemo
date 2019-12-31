package com.buzz.jwtdemo.exception;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.buzz.jwtdemo.common.ResponseConstants;
import com.buzz.jwtdemo.enumerate.EnumMessage;


@RestControllerAdvice
public class JwtControllerAdvice {

	private static Logger _logger = LoggerFactory.getLogger(JwtControllerAdvice.class);
	
	/**********************************************************************************************
	 * @PreAuthorize 어노테이션이 사용된 컨트롤러중 권한이 없어서 발생하는 오류 Catch.......
	 * API 주소레벨 권한은 있지만 메소드 레벨에 권한이 없는 경우 
	 * api.jwtserver.com/user/v1/secure 의 URI 에서 USER 권한으로 요청하는 경우
	 * api.jwtserver.com/user/* 에 대해서 USER 롤으로 접근이 가능 하고 /sercure 는 ADMIN 만 
	 * 접근이 가능 한 경우 
	 **********************************************************************************************/
	@ExceptionHandler(AccessDeniedException.class) 
	public ResponseEntity<HashMap<String,Object>> accessDeniedException() {
		HashMap<String,Object> result = new HashMap<String,Object>();
	        
		result.put(ResponseConstants.STATUS , ResponseConstants.RESULT_NOTROLE);
	    result.put(ResponseConstants.MESSAGE, EnumMessage.ERROR_URL_FORBIDDEN.getMessage());
	            
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
	    result.put(ResponseConstants.MESSAGE, EnumMessage.ERROR_URL_NOTFOUND.getMessage() );
	            
	    return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.NOT_FOUND);
	}
	
	/**********************************************************************************************
	 * 사용자 오류	 
	 **********************************************************************************************/
	@ExceptionHandler(UserRuntimeException .class)
	public ResponseEntity<HashMap<String,Object>> userRuntimeException(UserRuntimeException ex) {
		HashMap<String,Object> result = new HashMap<String,Object>();
	    
		result.put(ResponseConstants.STATUS , ResponseConstants.RESULT_ERROR);
	    result.put(ResponseConstants.MESSAGE, ex.getUserError());
	            
	    return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**********************************************************************************************
	 * 일반적인 오류	 
	 **********************************************************************************************/
	@ExceptionHandler(Exception.class)
	public ResponseEntity<HashMap<String,Object>> normalException(Exception ex) {
		HashMap<String,Object> result = new HashMap<String,Object>();
	    
		_logger.error("Controller Exception : {}",ex.getLocalizedMessage());
		
		result.put(ResponseConstants.STATUS , ResponseConstants.RESULT_ERROR);
	    result.put(ResponseConstants.MESSAGE, EnumMessage.ERROR_REQUEST.getMessage());
	            
	    return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	 
}
