package com.buzz.jwtdemo.enumerate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnumMapperValue {
	private String code;
	private String title;
	
	public EnumMapperValue(EnumMapperType enumMapperType) {
		code = enumMapperType.getCode();
		title =  enumMapperType.getTitle();
	}	
}
