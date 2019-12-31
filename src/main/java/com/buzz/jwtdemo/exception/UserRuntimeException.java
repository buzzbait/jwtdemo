package com.buzz.jwtdemo.exception;

public class UserRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String userMessage;
	
	public UserRuntimeException(Throwable cause, String errorMessage,String userMessage ) {
		super(errorMessage,cause);
		this.userMessage = userMessage;
	}
	public UserRuntimeException(String userMessage ) {
		super(userMessage);
		this.userMessage = userMessage;
	}
	
	public String getUserError() {
		return userMessage;
	}
	
}
