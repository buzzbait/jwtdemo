package com.buzz.jwtdemo.common;

public class JwtMessageKey {
	
	//공통 에러코드는 -1,0 으로 설정
	public static final int REQUEST_SUCCESS = 0;
	public static final int REQUEST_ERROR = -1;
	
	//JWT 에러관련은 -10번 대역
	public static final int JWT_ERROR_EXPIRED = -10;
	public static final int JWT_ERROR_INVALID = -11;
	
	//메시지 KEY 설정
	public static final String RESPONSE_NOTFOUND  	= "response.nofound";
	public static final String RESPONSE_FORBIDDEN  	= "response.forbidden";
	public static final String RESPONSE_NOTAUTH 	= "response.notauth";	
	public static final String RESPONSE_OK 			= "response.ok";
	public static final String RESPONSE_ERROR 		= "response.error";
		
		
}
