package com.buzz.jwtdemo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

/********************************************************************************************************************
 * 모든 Entity 에서 공통으로 사용하는 필드 정의
 * 해당 Entity 에서 상속 받아서 사용 한다.
 ********************************************************************************************************************/
@Getter
@Setter
//@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseCommonEntity implements Serializable {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;
	
	/*@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = true)
	protected java.util.Date updDtm;	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	protected java.util.Date crtDtm;*/
	
	@LastModifiedDate
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(insertable = false, updatable = true)
	protected LocalDateTime  updDtm;
	
	@CreatedDate
	@Column(insertable = true, updatable = false)
	@Convert(converter = LocalDateTimePersistenceConverter.class) 
	protected LocalDateTime  crtDtm;
	
}
