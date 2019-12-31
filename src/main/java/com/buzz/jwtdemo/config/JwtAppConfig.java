package com.buzz.jwtdemo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.buzz.jwtdemo.enumerate.EnumActiveStatus;
import com.buzz.jwtdemo.enumerate.EnumMapper;

@Configuration
public class JwtAppConfig {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public EnumMapper enumMapper() {
		EnumMapper enumMapper =  new EnumMapper();
		
		//모든 코드 enum 클래스 등록
		enumMapper.put("ACTIVE_STATUS",EnumActiveStatus.class );
		
		return enumMapper;
	}
}
