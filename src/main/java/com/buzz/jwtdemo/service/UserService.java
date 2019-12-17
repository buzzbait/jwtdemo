package com.buzz.jwtdemo.service;

import java.util.HashMap;


import org.springframework.stereotype.Service;

import com.buzz.jwtdemo.common.JwtMessageKey;
import com.buzz.jwtdemo.common.MessageUtil;
import com.buzz.jwtdemo.common.ResponseConstants;

@Service
public class UserService extends JwtBaseService{
	
	public HashMap<String,Object> info(){
		HashMap<String,Object> result = this.makeResultMap();
		
		//response flag 설정
		result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
		result.put(ResponseConstants.MESSAGE,MessageUtil.getMessage(JwtMessageKey.MSG_KEY_OK ) );
		
		return result;
	}
	
}
