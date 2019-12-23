package com.buzz.jwtdemo.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.Builder;
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
@EqualsAndHashCode(of = {"id"})
public class MemberRole {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;	
	
	/********************************************************************************************************
	 * MEMBERROLE 테이블의 MEMBER_ID 필드가 FK 
	 * @JoinColumn(name = "MEMBER_ID") -> MEMBER_ID 필드가 FK 로 MEMBER 테이블의 PK 에 연결 
	 ********************************************************************************************************/	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "MEMBER_ID",nullable = false)	
	private Member member;
	
	/********************************************************************************************************
	 * meber <-> memberrole <-> role
	 * memberrole 테이블은  member 가 가지고 있는 롤정보를 매핑해주는 테이블,role 은 권한정보 관리 테이블
	 * FK는 자신의 테이블(MemberRole) 에 있다.
	 ********************************************************************************************************/
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	private Role role;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private java.util.Date crtDtm;
		
	@Builder
	public MemberRole(Member member, Role role) {
		this.member = member;
		this.role = role;
	}
}
