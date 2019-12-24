package com.buzz.jwtdemo.domain.memberrole;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.buzz.jwtdemo.domain.BaseCommonEntity;
import com.buzz.jwtdemo.domain.member.MemberEntity;
import com.buzz.jwtdemo.enumerate.RoleName;

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
public class MemberRoleEntity extends BaseCommonEntity {

	/********************************************************************************************************
	 * MEMBERROLE 테이블의 MEMBER_ID 필드가 FK 
	 * @JoinColumn(name = "MEMBER_ID") -> MEMBER_ID 필드가 FK 로 MEMBER 테이블의 PK 에 연결 
	 ********************************************************************************************************/	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "MEMBER_ID",nullable = false)	
	private MemberEntity member;
	
	@Enumerated(EnumType.STRING)
    private RoleName roleName;		
		
	@Builder
	public MemberRoleEntity(MemberEntity member, RoleName role) {
		this.member = member;
		this.roleName = role;
	}
}
