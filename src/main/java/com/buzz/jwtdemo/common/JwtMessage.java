package com.buzz.jwtdemo.common;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@Deprecated
public class JwtMessage {
	
	private MessageSource messageSource = null;
		
	
	public String getMessage(String messageKey) {
		//메시지 반환
		return this.messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
	}
}
