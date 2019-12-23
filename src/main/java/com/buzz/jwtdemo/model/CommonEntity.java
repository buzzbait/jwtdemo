package com.buzz.jwtdemo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/********************************************************************************************************************
 * 모든 Entity 에서 공통으로 사용하는 필드 정의
 * 해당 Entity 에서 상속 받아서 사용 한다.
 ********************************************************************************************************************/
@MappedSuperclass
public class CommonEntity {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	protected java.util.Date crtDtm;	
}
