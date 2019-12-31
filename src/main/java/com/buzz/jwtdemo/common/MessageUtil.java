package com.buzz.jwtdemo.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/****************************************************************************************************
 * MessageUtil 클래스
 * 사용법 : MessageUtil.getMessage(JwtMessageKey.MSG_KEY_ERROR)
 * 메시지는 리소스를 사용하지 않고 Enum 으로 처리
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
