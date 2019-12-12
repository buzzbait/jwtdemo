package com.buzz.jwtdemo.exception;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/******************************************************************************************************
 * 인증되지 않는 Request에 대한 문제해결
 * 컨트롤러 이전 필터링에서의 커스텀 오류 작성
 *****************************************************************************************************/
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException,ServletException {
        response.sendRedirect("/exception/entrypoint");
    }
}