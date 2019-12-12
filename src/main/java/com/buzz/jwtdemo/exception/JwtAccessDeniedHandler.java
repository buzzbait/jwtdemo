package com.buzz.jwtdemo.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/******************************************************************************************************
 * 허용되지 않은 URL 에 접근하는 경우 처리 
 *****************************************************************************************************/
public class JwtAccessDeniedHandler implements AccessDeniedHandler  {
	
	@Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException,
            ServletException {
        
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        
        JSONObject json = new JSONObject();        
        json.put("status_code", -1);
        json.put("message", "접근 권한 오류 입니다");
        
        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
