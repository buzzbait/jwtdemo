package com.buzz.jwtdemo.common;

public class JwtMessageKey {
	
	//공통 에러코드는 -1,0 으로 설정
	public static final int REQUEST_SUCCESS 	= 0;
	public static final int REQUEST_ERROR 		= -1;
	public static final int REQUEST_NOTFOUND 	= -2;
	public static final int REQUEST_NOTROLE 	= -3;
	
	//JWT 에러관련은 -10번 대역
	public static final int JWT_ERROR_EXPIRED = -10;
	public static final int JWT_ERROR_INVALID = -11;
	
	//메시지 KEY 설정
	public static final String MSG_KEY_NOTFOUND  	= "response.nofound";
	public static final String MSG_KEY_FORBIDDEN  	= "response.forbidden";
	public static final String MSG_KEY_NOTAUTH 		= "response.notauth";	
	public static final String MSG_KEY_OK 			= "response.ok";
	public static final String MSG_KEY_ERROR 		= "response.error";
		
		
}
