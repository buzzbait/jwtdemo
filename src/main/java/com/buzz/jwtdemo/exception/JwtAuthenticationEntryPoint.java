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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.buzz.jwtdemo.common.JwtMessageKey;
import com.buzz.jwtdemo.common.MessageUtil;
import com.buzz.jwtdemo.common.ResponseKey;


/******************************************************************************************************
 * 인증되지 않는 Request에 대한 문제해결(인증정보가 없는 경우)
 * JWT 검증시 만료시간이 지났거나 검증 오류시 인증정보가 SecurityContextHolder 에 저장 되지 않은 경우
 * 컨트롤러 이전 필터링에서의 커스텀 오류 작성
 *****************************************************************************************************/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static Logger _logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
		
	/******************************************************************************************************
	 * OncePerRequestFilter doFilter 적용시 Jwt 인증 오류시 수행
	 ******************************************************************************************************/
	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException,ServletException {
		_logger.debug("Auth Error :{}",ex.getMessage());
		       
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        
        HashMap<String, Object> responseMap  = new HashMap<String, Object>();
        
        //인증키 유효기간 만료 여부 검사
        final String expired = (String)request.getAttribute("expired");
        
        if(expired != null) {
        	//인증키 유효기간 만료
        	responseMap.put(ResponseKey.STATUS.keyName() , JwtMessageKey.JWT_ERROR_EXPIRED);
        	responseMap.put(ResponseKey.MESSAGE.keyName(), MessageUtil.getMessage("response.expiredjwt"));
        }else {        
        	responseMap.put(ResponseKey.STATUS.keyName() , JwtMessageKey.JWT_ERROR_INVALID);
        	responseMap.put(ResponseKey.MESSAGE.keyName(),MessageUtil.getMessage("response.invalidjwt" ));
        }
        
        JSONObject json = new JSONObject(responseMap);
        
        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
