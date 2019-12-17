package com.buzz.jwtdemo.common;

/*****************************************************************************************************
 * Resonse 관련 key 관련
 *****************************************************************************************************/
public class ResponseConstants {
		
	public static final String STATUS 	=  "status";
	public static final String MESSAGE 	=  "message";
	public static final String DATA 	=  "data";
	
	public static final int RESULT_SUCCESS 	= 0;
	//일반적인 오류는 -1~-99 대역
	public static final int RESULT_ERROR 	= -1;
	public static final int RESULT_NOTFOUND	= -2;
	public static final int RESULT_NOTROLE 	= -3;
	
	//JWT 에러관련은 -100번 대역
	public static final int RESULT_JWTEXPIRED = -100;
	public static final int RESULT_JWTINVALID = -101;
		
}
