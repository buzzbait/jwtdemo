package com.buzz.jwtdemo.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


/******************************************************************************************************
 * 인증되지 않는 Request에 대한 문제해결
 * 컨트롤러 이전 필터링에서의 커스텀 오류 작성
 *****************************************************************************************************/
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
	
	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException,ServletException {
		logger.debug("commence ex :{}",ex.getMessage());
        //response.sendRedirect("/exception/entrypoint");        
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        
        JSONObject json = new JSONObject();        
        json.put("status_code", -1);
        json.put("message", "인증되지 않은 접근 입니다");
        
        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
