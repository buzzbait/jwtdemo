package com.buzz.jwtdemo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.Builder;

/**********************************************************************************************************
 * @Entity : 해당클래스의 인스턴스들이 엔티티임을 명시한다.(javax.persistence.Entity)
 * @Table  : 설정하는 경우에는 기본적으로 클래스명과 동일한 테이블을 지정 
 *           만일클래스명과 다른 이름의 테이블을 사용하는 경우 name 값을 테이블명으로 작성            
 * ---------------------------------------------------------------------------------------------------------          
 * @Id	: 해당컬럼이 Primary key 라는것을 의미		      
 * @GeneratedValue : @Id 와 함께 사용되며 식별키를 어떤 방식으로 생성하는지를 명시
 *                   IDENTITY 는 기본키 생성방식 자체를 데이터베이스에 위임하는 방식이며 주로 MySql(MariaDB)
 *                   에서 사용
 **********************************************************************************************************/

/**********************************************************************************************************
 * [lombok 사용]
 * @AllArgsConstructor, @RequiredArgsConstructor,@Data,@Value 사용금지
 * @EqualsAndHashCode 사용시 변경가능한 객체는 추가 하지 않는다(동일 객체로 판단이 안된다)
 * @ToSting 사용시 참조필드는 제외 할 것
 * EntityManager 가 Entity 를 사용하기 위해선 기본 생성자가 반드시 필요한데 프로그램 내에 아무곳에서나 
 * Entity 를 생성하지 않도록 lombok lib 에서 제공하는 @NoArgsConstructor 어노테이션에 
 * access = AccessLevel.PROTECTED 속성을 정의
 * 기본매핑 네이밍 규칙은 Entity(camelCase) - Table(SNAME_HEAD) 이다
 **********************************************************************************************************/

@Entity
@Table(name="MEMBER")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id", "loginId"})
public class Member {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	
	private String loginId;
	private String loginPass;
	
	@Basic(optional = false)
	@Column(name = "CRT_DTM", insertable = false, updatable = false)	
	private LocalDateTime crtDtm;
	
	/********************************************************************************************************
	 * 매핑은 일단(무조건) 최대한 단방향으로 설계 한다
	 * fetch는 기본적으로 LAZY 로 발라버려라...(성능 이슈 특별한 경우에만  다른 값으로 설정)
	 * 멤버가 N개의 롤을 소유 할수 있기 때문에 1:N 관계 @One(Member)ToMany(MemberRole)	  
	 * @JoinColumn 은 클래스에 선언된 멤버변수명으로 지정
	 ********************************************************************************************************/	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId") //MemberRole 의 FK(memberId)에 조인 설정
	private List<MemberRole> memberRoles = new ArrayList<MemberRole>();
	
	@Builder
	public Member(String loginId, String loginPass,List<MemberRole> memberRoles) {
		this.loginId = loginId;
	    this.loginPass = loginPass;
	    this.memberRoles = memberRoles;
	}
}
