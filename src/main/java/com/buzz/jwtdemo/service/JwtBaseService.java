package com.buzz.jwtdemo.service;

import java.util.HashMap;

import com.buzz.jwtdemo.common.JwtMessageKey;
import com.buzz.jwtdemo.common.MessageUtil;
import com.buzz.jwtdemo.common.ResponseConstants;

/****************************************************************************************************
 * Service Layer 의 공통 로직 구현
 ****************************************************************************************************/
public class JwtBaseService {

	protected HashMap<String,Object> makeResultMap() {
		
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
		resultMap.put(ResponseConstants.MESSAGE,MessageUtil.getMessage(JwtMessageKey.MSG_KEY_OK ) );
		
		return resultMap;
	}	
		
}
