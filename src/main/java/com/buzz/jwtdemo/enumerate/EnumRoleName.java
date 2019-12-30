package com.buzz.jwtdemo.enumerate;

import com.fasterxml.jackson.annotation.JsonFormat;

/***************************************************************************************************
 *  ROLE Entity 의 RoleName Enum 설정
 *  실제 Spring Security 에 넘길때는 앞에 "ROLE_" 이 붙어야 한다
 ***************************************************************************************************/
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum EnumRoleName {
	ADMIN
	,USER
	,GUEST
}
