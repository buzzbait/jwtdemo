package com.buzz.jwtdemo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buzz.jwtdemo.common.ResponseConstants;
import com.buzz.jwtdemo.service.UserService;

/*******************************************************************************************************************
 * API 버저닝은 URL/서비스명/버저닝/메소드명
 * ex) api.jwtservice.com/user/v1/info
 * 
 * Controller의 Return Type 은 ResponseEntity<T> 로 설정하고 Service layer 의 Return Type 은 T로 지정 한다
 * 롤체크는 @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')") 를 사용한다
 *******************************************************************************************************************/
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService _userService;
	
	@GetMapping({"/v1/info", "/v1.5/info"})
    public ResponseEntity<HashMap<String,Object>> userInfo(@RequestParam String id) {
    	
		HashMap<String,Object> result =  _userService.info();		
    	return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.OK);        
    }
	
	@GetMapping({"/v1/allmember", "/v1.5/allmember"})
    public ResponseEntity<HashMap<String,Object>> allmember() {
    	
		HashMap<String,Object> result =  _userService.allMember();		
    	return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.OK);        
    }
	
	//PostMapping -> Insert
	@PostMapping({"/v1/manage", "/v1.5/manage"})
    public ResponseEntity<HashMap<String,Object>> newUser(@RequestBody HashMap<String,Object> user) {
    	
		HashMap<String,Object> result =  _userService.addUser(user);		
    	return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.OK);        
    }
	
	//PutMapping -> Update
	@PutMapping({"/v1/manage", "/v1.5/manage"})
    public ResponseEntity<HashMap<String,Object>> updateUser(@RequestBody HashMap<String,Object> user) {
    	
		HashMap<String,Object> result =  _userService.updateUser(user);		
    	return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.OK);        
    }
	
	//DeleteMapping -> Delete
	@DeleteMapping({"/v1/manage", "/v1.5/manage"})
    public ResponseEntity<HashMap<String,Object>> deleteUser(@RequestBody HashMap<String,Object> user) {
    	
		HashMap<String,Object> result =  _userService.deleteUser(user); 		
    	return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.OK);        
    }
		
	/*
	 * @Secured는 표현식 사용할 수 없고,@PreAuthroize는 표현식 사용 가능
	 * @EnableGlobalMethodSecurity(prePostEnabled = true) 를 설정해여 작동 한다
	 * @PreAuthroize 에서 발생되는 오류는 ControllerAdvice 에서 처리 한다
	 * /user API는 security filter 에서 ROLE_USER 로 설정 되었기 때문에 USER 권한으로 userSecure 메소드 까지는 올수 있다.
	 * @PreAuthorize("hasRole('ROLE_ADMIN')") 를 사용하게 되면 ADMIN 권한만 사용할수 있기 때문에 ControllerAdvice 에서 
	 * AccessDeniedException 을 catch 해서 처리 한다 
	 */	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/secure")
    public ResponseEntity<HashMap<String,Object>> userSecure(@RequestParam String id) {
    	
		HashMap<String,Object> result =  new HashMap<String,Object>();		
		result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
		
		return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.OK);    
    }
}
