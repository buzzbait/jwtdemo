package com.buzz.jwtdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class JwtBaseService {

	@Autowired
	private MessageSource _messageSource;
	
	public String getMessage(String messageKey) {
		return _messageSource.getMessage(messageKey,null,LocaleContextHolder.getLocale() );
	}
	
	public String getOkMessage() {
		return _messageSource.getMessage("response.ok",null,LocaleContextHolder.getLocale() );
	}
	
	public String getErrorMessage() {
		return _messageSource.getMessage("response.error",null,LocaleContextHolder.getLocale() );
	}
}
