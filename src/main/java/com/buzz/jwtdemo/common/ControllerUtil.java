package com.buzz.jwtdemo.common;

import java.util.HashMap;
import java.util.List;

import com.buzz.jwtdemo.enumerate.EnumMessage;
import com.buzz.jwtdemo.exception.UserRuntimeException;

public class ControllerUtil {

	//
	public static void verifyParams(HashMap<String,Object> params, List<String> verifyParams ) {
		
		if( (params == null) || (params.size() == 0)) {
			throw new UserRuntimeException( EnumMessage.ERROR_URL_PARAMETER.getMessage());
		}
				
		for(String verifyParam : verifyParams) {
			if(! params.containsKey(verifyParam)) {
				//필수파라미터가 없는 경우
				throw new UserRuntimeException(EnumMessage.ERROR_URL_PARAMETER.getMessage());
			}
		}		
		
	}
}
