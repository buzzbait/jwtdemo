package com.buzz.jwtdemo.security;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**********************************************************************************************************************
 * spring은 filter에서 spring config 설정 정보를 쉽게 처리하기 위한 GenericFilterBean을 제공한다.
 * 또한 Filter를 중첩 호출한 경우 (의도치 않은 경우) 매번 Filter의 내용이 수행되는 것을 방지하기 위해 GenericFilterBean을 상속한 
 * OncePerRequestFilter도 있다.
 * OncePerRequestFilter를 상속하여 구현한 경우 doFilter 대신 doFilterInternal 메서드를 구현하면 된다.
 * **********************************************************************************************************************/
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	private JwtTokenProvider jwtTokenProvider;
	 
    // Jwt Provier 주입
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
	
	// Request로 들어오는 Jwt Token의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter를 filterChain에 등록합니다.
	@Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		logger.debug("doFilterInternal......");
		
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        
		if (token != null && jwtTokenProvider.validateToken(token,req)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            //SecurityContextHolder에서는 보안 주체의 세부 정보를 포함하여 응용 프로그램의 현재 보안 컨텍스트에 대한 세부 정보가 저장된다
            //인증이 된 경우에만 SecurityContextHolder 에 저장 한다 
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
         
		// TODO 전처리 
		chain.doFilter(req, res); 
		// TODO 후처리		
	}
	
	
}
