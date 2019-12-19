package com.buzz.jwtdemo.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.buzz.jwtdemo.common.JwtMessageKey;
import com.buzz.jwtdemo.common.MessageUtil;
import com.buzz.jwtdemo.common.ResponseConstants;

@Service
public class UserService extends JwtBaseService{
	
	private static Logger _logger = LoggerFactory.getLogger(UserService.class);
	
	public HashMap<String,Object> info(){
		HashMap<String,Object> result = this.makeResultMap();
		
		//response flag 설정
		result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
		result.put(ResponseConstants.MESSAGE,MessageUtil.getMessage(JwtMessageKey.MSG_KEY_OK ) );
		
		return result;
	}
	
	public HashMap<String,Object> addUser(HashMap<String,Object> user){
		HashMap<String,Object> result = this.makeResultMap();
		
		_logger.debug("add user : {} ",user.get("userId").toString());
		
		//response flag 설정
		result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
		result.put(ResponseConstants.MESSAGE,MessageUtil.getMessage(JwtMessageKey.MSG_KEY_OK ) );
		
		return result;
	}
	
	public HashMap<String,Object> updateUser(HashMap<String,Object> user){
		HashMap<String,Object> result = this.makeResultMap();
		
		_logger.debug("update user : {} ",user.get("userId").toString());
		
		//response flag 설정
		result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
		result.put(ResponseConstants.MESSAGE,MessageUtil.getMessage(JwtMessageKey.MSG_KEY_OK ) );
		
		return result;
	}
	
	public HashMap<String,Object> deleteUser(HashMap<String,Object> user){
		HashMap<String,Object> result = this.makeResultMap();
		
		_logger.debug("delete user : {} ",user.get("userId").toString());
		
		//response flag 설정
		result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
		result.put(ResponseConstants.MESSAGE,MessageUtil.getMessage(JwtMessageKey.MSG_KEY_OK ) );
		
		return result;
	}
	
}
