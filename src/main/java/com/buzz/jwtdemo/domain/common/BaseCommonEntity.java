package com.buzz.jwtdemo.domain.common;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

/********************************************************************************************************************
 * 모든 Entity 에서 공통으로 사용하는 필드 정의
 * 해당 Entity 에서 상속 받아서 사용 한다.
 * AuditingEntityListener 사용을 위해 @EnableJpaAuditing 를 추가해야 한다( @LastModifiedDate,@CreatedDate 자동설정)
 ********************************************************************************************************************/
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseCommonEntity{
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
		
	@LastModifiedDate
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(insertable = false, updatable = true)
	protected LocalDateTime  updDtm;
	
	@CreatedDate
	@Column(insertable = true, updatable = false)
	@Convert(converter = LocalDateTimePersistenceConverter.class) 
	protected LocalDateTime  crtDtm;
	
}
