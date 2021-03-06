package com.buzz.jwtdemo.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.buzz.jwtdemo.common.JwtMessageKey;
import com.buzz.jwtdemo.common.MessageUtil;
import com.buzz.jwtdemo.common.ResponseConstants;

/******************************************************************************************************
 * 허용되지 않은 URL 에 접근하는 경우 처리 
 * JWT 인증 처리된후 해당 리소스에 권한이 없는경우 수행
 *****************************************************************************************************/
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler  {
	
	@Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException,
            ServletException {
        
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        
        HashMap<String, Object> responseMap  = new HashMap<String, Object>();
        
        responseMap.put(ResponseConstants.STATUS , ResponseConstants.RESULT_NOTROLE);
        responseMap.put(ResponseConstants.MESSAGE ,MessageUtil.getMessage(JwtMessageKey.MSG_KEY_FORBIDDEN ));
        
        JSONObject json = new JSONObject(responseMap);
                
        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
