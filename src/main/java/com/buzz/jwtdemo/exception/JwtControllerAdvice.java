package com.buzz.jwtdemo.exception;

import java.util.HashMap;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.buzz.jwtdemo.common.FinalValue;
import com.buzz.jwtdemo.common.ResponseKey;

@RestControllerAdvice
public class JwtControllerAdvice {

	/**********************************************************************************************
	 * @PreAuthorize 어노테이션이 사용된 컨트롤러중 권한이 없어서 발생하는 오류 Catch....... 
	 **********************************************************************************************/
	@ExceptionHandler(AccessDeniedException.class) 
	public HashMap<String,Object> accessDeniedException() {
		HashMap<String,Object> result = new HashMap<String,Object>();
	        
	    result.put(ResponseKey.STATUS.keyName(), FinalValue.REQUEST_ERROR);
	    result.put(ResponseKey.MESSAGE.keyName(),"접근 권한 오류 입니다");        
	    return result;
	}	
	
	/**********************************************************************************************
	 * 404 Error Catch
	 * 404 Error 를 Catch 하기 위해서는 환경파일에 아래와 같은 설정이 추가되어야 함
	 * spring.mvc.throw-exception-if-no-handler-found=true
	 * spring.resources.add-mappings=false	 
	 **********************************************************************************************/
	@ExceptionHandler(NoHandlerFoundException.class)
	public HashMap<String,Object> noHandlerFoundException() {
		HashMap<String,Object> result = new HashMap<String,Object>();
	        
		result.put(ResponseKey.STATUS.keyName(),FinalValue.REQUEST_ERROR);
	    result.put(ResponseKey.MESSAGE.keyName(),"해당요청은 존재하지 않습니다");
	            
	    return result;
	}
	
	/**********************************************************************************************
	 * 404 Error Catch
	 * 404 Error 를 Catch 하기 위해서는 환경파일에 아래와 같은 설정이 추가되어야 함
	 * spring.mvc.throw-exception-if-no-handler-found=true
	 * spring.resources.add-mappings=false	 
	 **********************************************************************************************/
	@ExceptionHandler(Exception.class)
	public HashMap<String,Object> normalException() {
		HashMap<String,Object> result = new HashMap<String,Object>();
	        
		result.put(ResponseKey.STATUS.keyName(), FinalValue.REQUEST_ERROR);
	    result.put(ResponseKey.MESSAGE.keyName(),"오류가 발생 했습니다");
	            
	    return result;
	}
	 
}
