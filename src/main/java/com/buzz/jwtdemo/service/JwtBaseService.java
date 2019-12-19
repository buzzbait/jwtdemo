package com.buzz.jwtdemo.service;

import java.util.HashMap;

/****************************************************************************************************
 * Service Layer 의 공통 로직 구현
 ****************************************************************************************************/
public class JwtBaseService {

	protected HashMap<String,Object> makeResultMap() {
		return new HashMap<String,Object>();
	}	
		
}
