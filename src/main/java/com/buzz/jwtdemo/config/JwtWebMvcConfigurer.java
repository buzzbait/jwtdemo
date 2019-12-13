package com.buzz.jwtdemo.config;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@Configuration
public class JwtWebMvcConfigurer implements WebMvcConfigurer{

	private static Logger logger = LoggerFactory.getLogger(JwtWebMvcConfigurer.class);
	
	@Bean
	public MessageSource messageSource() {
		
		logger.debug("messageSource .............");
		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/message");
		messageSource.setDefaultEncoding("UTF-8");
		
		// 없는 메세지일 경우 예외를 발생시키는 대신 코드를 기본 메세지로 한다.
		messageSource.setUseCodeAsDefaultMessage(true);
		
		return messageSource;
	}

	// 세션에 지역설정. default는 KOREAN = 'ko'
	@Bean
	public LocaleResolver localeResolver() {
		logger.debug("localeResolver .............");
		SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.KOREAN);
		return slr;
	}

	// 지역설정을 변경하는 인터셉터. 요청시 파라미터에 lang 정보를 지정하면 언어가 변경됨.
	@Bean 
    public LocaleChangeInterceptor localeChangeInterceptor() {
		logger.debug("localeChangeInterceptor .............");
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
	
	//인터셉터를 시스템 레지스트리에 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.debug("addInterceptors .............");
		registry.addInterceptor(localeChangeInterceptor());
	}
}
