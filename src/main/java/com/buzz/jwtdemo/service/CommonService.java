package com.buzz.jwtdemo.service;


import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buzz.jwtdemo.common.ResponseConstants;

import com.buzz.jwtdemo.enumerate.EnumMapper;

@Service
@Transactional
public class CommonService extends JwtBaseService{

	@Autowired
	private EnumMapper _enumMapper;
	
	@Transactional(readOnly = true)
	public HashMap<String,Object> getCodeList(String codeKey){
		
		HashMap<String,Object> result = this.makeResultMap();						
		result.put(ResponseConstants.DATA, _enumMapper.get(codeKey));
				
		return result;		
	}
}
