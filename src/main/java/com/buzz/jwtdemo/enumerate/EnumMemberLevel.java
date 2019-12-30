package com.buzz.jwtdemo.enumerate;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum EnumMemberLevel {
	STAFF,  //0
	LEADER,	//1
	MANAGER	//2
}
