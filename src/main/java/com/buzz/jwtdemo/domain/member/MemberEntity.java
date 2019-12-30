package com.buzz.jwtdemo.domain.member;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.buzz.jwtdemo.domain.common.BaseCommonEntity;
import com.buzz.jwtdemo.enumerate.EnumActiveStatus;
import com.buzz.jwtdemo.enumerate.EnumMemberLevel;
import com.buzz.jwtdemo.enumerate.EnumRoleName;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class MemberEntity extends BaseCommonEntity {
		
	private String loginId;
	private String loginPass;
	
	@Enumerated(EnumType.ORDINAL)
    private EnumActiveStatus activeStatus;		
	
	@Enumerated(EnumType.ORDINAL)
    private EnumMemberLevel level;
	
	@Builder
	public MemberEntity(String loginId, String loginPass,EnumMemberLevel level) {
		this.loginId = loginId;
	    this.loginPass = loginPass;	
	    this.activeStatus = EnumActiveStatus.ON;
	    this.level = level;	    
	}
}

