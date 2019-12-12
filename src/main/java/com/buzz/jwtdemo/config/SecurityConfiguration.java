package com.buzz.jwtdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.buzz.jwtdemo.exception.JwtAccessDeniedHandler;
import com.buzz.jwtdemo.exception.JwtAuthenticationEntryPoint;
import com.buzz.jwtdemo.security.JwtAuthenticationFilter;
import com.buzz.jwtdemo.security.JwtTokenProvider;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	/*
	 * [Filter Chain]
	 * 클라이언트가 리소스를 요청할 때 접근 권한이 없는 경우 기본적으로 로그인 폼으로 보내게 되는데 그 역할을 하는 필터는 UsernamePasswordAuthenticationFilter입니다.
	 * Rest Api에서는 로그인 폼이 따로 없으므로 인증 권한이 없다는 오류 Json을 내려줘야 하므로	UsernamePasswordAuthenticationFilter 전에 관련 필터를 삽입한다
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
            .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증하므로 세션은 필요없으므로 생성안함.
            .and()
                .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
                    .antMatchers("/*/signin", "/*/signup").permitAll() // 가입 및 인증 주소는 누구나 접근가능
                    .antMatchers(HttpMethod.GET, "/exception/**").permitAll() // exception로 시작하는 GET요청 리소스는 누구나 접근가능
                    .antMatchers("/admin/*").hasRole("ADMIN") //admin 으로 시작 하는 URI 는 관리자만 접근 가능                    
                    .anyRequest().hasAnyRole("USER","ADMIN")  //그외 로그인이 인증된 모든 사용자 가능
            .and()
            	.exceptionHandling().accessDeniedHandler(new JwtAccessDeniedHandler()) //Access권한 오류 핸들러
            .and()
            	.exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint()) //비정상적인 호출 오류 핸들러
            .and()            	
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // JwtAuthenticationFilter 를 UsernamePasswordAuthenticationFilter 이전에 등록한다
 
    }
 	
    @Override // ignore check swagger resource
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**");
 
    }
    
}
