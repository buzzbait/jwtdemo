package com.buzz.jwtdemo.domain.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**********************************************************************************************************
 * 공통으로 사용하는 DTO 정의
***********************************************************************************************************/
@Getter
@Setter
public abstract class CommonDto {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
	private LocalDateTime updDtm;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
	private LocalDateTime crtDtm;
}
