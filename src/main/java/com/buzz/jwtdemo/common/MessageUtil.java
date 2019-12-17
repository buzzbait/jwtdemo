package com.buzz.jwtdemo.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/****************************************************************************************************
 * MessageUtil 클래스
 ****************************************************************************************************/
@Component
public class MessageUtil {

	private static MessageSource _messageSource = null;
	
	@Autowired
    private MessageSource _initMessageSource;
	
	@PostConstruct
    private void initStaticMessage() {    

		_messageSource = this._initMessageSource;
    }
	
	public static String getMessage(String messageKey) {
		return _messageSource.getMessage(messageKey,null,LocaleContextHolder.getLocale() );
	}
	
}
