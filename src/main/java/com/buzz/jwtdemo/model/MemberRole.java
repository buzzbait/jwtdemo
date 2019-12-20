package com.buzz.jwtdemo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="MEMBERROLE")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id", "memberId" })
public class MemberRole {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	
	private Long memberId;	
	
	/********************************************************************************************************
	 * meber <-> memberrole <-> role
	 * memberrole 테이블은  member 가 가지고 있는 롤정보를 매핑해주는 테이블,role 은 권한정보 관리 테이블
	 ********************************************************************************************************/
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Role role;
	
	private LocalDateTime crtDtm;
}
