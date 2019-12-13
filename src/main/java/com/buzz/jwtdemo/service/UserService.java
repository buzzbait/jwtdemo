package com.buzz.jwtdemo.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.buzz.jwtdemo.common.JwtMessageKey;
import com.buzz.jwtdemo.common.ResponseKey;

@Service
public class UserService extends JwtBaseService{

	public HashMap<String,Object> info(){
		HashMap<String,Object> result =  new HashMap<String,Object>();
		
		result.put(ResponseKey.STATUS.keyName(), JwtMessageKey.REQUEST_SUCCESS);
		result.put(ResponseKey.MESSAGE.keyName(),getOkMessage() );
		
		return result;
	}
	
}
