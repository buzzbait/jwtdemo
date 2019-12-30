package com.buzz.jwtdemo.domain.dto;

import java.time.LocalDateTime;

import com.buzz.jwtdemo.enumerate.EnumActiveStatus;
import com.buzz.jwtdemo.enumerate.EnumMemberLevel;
import com.buzz.jwtdemo.enumerate.EnumRoleName;

import lombok.Getter;
import lombok.Setter;

/************************************************************************************************
 * DTO 별로 클래스 파일을 생성하면 파일이 많아 지기 때문에 도메인에 static 으로 다 몰아 넣는다.
 * 여러 테이블 조인결과 DTO 의 경우 도메인에 맞추어 Class를 나누기 애매하기 때문에 논리적인 도메인에
 * 여러개의 DTO 클래스를 몰아 넣자
 * enum,datetime 형식은 모두 Enity 의 타입과 동일하게 맞추고 Json 변환시 규칙에 맞게 변환 해 준다.
 * enum 은 선언 클래스에서 문자열로 할지 순번으로 할지 지정해 준다 
 ************************************************************************************************/
public class MemberPojo {

	@Getter
	@Setter
	public static class MemberDto extends CommonDto{
		private Long Id;
		private String loginId;
		private String loginPass;	    
		private EnumActiveStatus activeStatus;
		private String activeStatusName;
		private EnumMemberLevel level;	
	}
	
	@Getter
	@Setter
	public static class MemberWithRoleDto extends CommonDto{
		private Long Id;
		private EnumRoleName roleName;
		private MemberDto memberDto;
	}
	
	
	@Getter
	@Setter
	public static class MemberRoleDto extends CommonDto{
		private Long Id;		
		private Long memberId;
		private String loginId;		
		private String loginPass;
		private EnumRoleName roleName;	
		private EnumActiveStatus activeStatus;
		private EnumMemberLevel level;		
	}
}
