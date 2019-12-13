package com.buzz.jwtdemo.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.buzz.jwtdemo.common.ResponseKey;


/******************************************************************************************************
 * 인증되지 않는 Request에 대한 문제해결(인증정보가 없는 경우)
 * 컨트롤러 이전 필터링에서의 커스텀 오류 작성
 *****************************************************************************************************/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static Logger _logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
	@Autowired
	private MessageSource _messageSource;
	
	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException,ServletException {
		_logger.debug("Filter Error :{}",ex.getMessage());
		       
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        
        HashMap<String, Object> responseMap  = new HashMap<String, Object>();
        
        responseMap.put(ResponseKey.STATUS.keyName() , -1);
        responseMap.put(ResponseKey.MESSAGE.keyName(),_messageSource.getMessage("response.notauth",null,LocaleContextHolder.getLocale() ));
        
        JSONObject json = new JSONObject(responseMap);
        
        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
