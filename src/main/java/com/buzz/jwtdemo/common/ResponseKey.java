package com.buzz.jwtdemo.common;

public enum ResponseKey {
	STATUS("status"),
	MESSAGE("message"),
	ERROR_CODE ("errorcode"),	
	DATA_RESULT("result");
	
	private String responseKeyName; 

	private ResponseKey(String msg) { 
		this.responseKeyName = msg; 
	}
	//Enum to String         
	public String keyName(){ 
		return this.responseKeyName; 
	} 
}
