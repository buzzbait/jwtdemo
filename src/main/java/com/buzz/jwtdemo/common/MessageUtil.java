package com.buzz.jwtdemo.common;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
/****************************************************************************************************
 * MessageUtil 클래스
 ****************************************************************************************************/
public class MessageUtil {

	private static MessageSource _messageSource = null;
	
	public static void setMessageSource(MessageSource messageSource) {
		_messageSource = messageSource;
	}
	
	public static String getMessage(String messageKey) {
		return _messageSource.getMessage(messageKey,null,LocaleContextHolder.getLocale() );
	}
}
