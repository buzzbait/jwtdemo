package com.buzz.jwtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/************************************************************************************************************
 * @EnableTransactionManagement
 * - 어노테이션 기반 트랜잭션 사용
 * - Spring boot 에서 spring-data-* 모듈 삽입시에 기본으로 적용된다.(cglib proxy 방식)
 * @EnableGlobalMethodSecurity
 * - 어노테이션을 사용하여 어노테이션 기반 보안을 적용
 * @EnableJpaAuditing
 * - Entity 의 생성일,수정일등 반복적으로 사용하는 필드에 대한 자동 설정값을 위해 활성화 
 ************************************************************************************************************/
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaAuditing
public class JwtdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtdemoApplication.class, args);
	}

}
