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
 * 인증되지 않는 Request에 대한 문제해결(인증정보가 없는 경우)
 * 컨트롤러 이전 필터링에서의 커스텀 오류 작성
 *****************************************************************************************************/
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
	
	//response.sendRedirect("/exception/entrypoint") 로 Controller 를 호출 하거나 바로 HttpServletResponse 에 write 한다.
	//HttpServletResponse 에 write 하는 것으로 구현
	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException,ServletException {
		logger.debug("Filter Error :{}",ex.getMessage());
		
        //        
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        
        JSONObject json = new JSONObject();        
        json.put("status_code", -1);
        json.put("message", "인증되지 않은 접근 입니다");
        
        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
