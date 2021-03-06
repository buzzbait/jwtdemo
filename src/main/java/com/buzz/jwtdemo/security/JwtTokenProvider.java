package com.buzz.jwtdemo.security;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.buzz.jwtdemo.model.JwtUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	private static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	//@Autowired
	//private CustomUserDetailService userDetailsService;
	
	// Jwt 토큰 생성
    public String createToken(String userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
    
        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(new Date(System.currentTimeMillis())) // 토큰 발행일자
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstants.ACCESS_TOKEN_VALIDITY_SECONDS*1000)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, JwtConstants.SIGNING_KEY) // 암호화 알고리즘, secret값 세팅
                .compact();
    }
    
    // Jwt 토큰으로 인증 정보를 조회
    // Filter 에서 매 요청시 수행됨
    // 토큰에 있는 정보를 기반으로 다시 DB를 조회 할 수도 있지만 토큰값만으로 인증을 할 수도 있다    
    public Authentication getAuthentication(String token) {
    	
    	//토큰 Body 정보
    	Claims claims = Jwts.parser().setSigningKey(JwtConstants.SIGNING_KEY).parseClaimsJws(token).getBody();
    	
    	//토큰 Body 에서 ID 검색
    	String userName = claims.getSubject();
    	//토큰에서 Body 에서 roles 정보 검색
    	@SuppressWarnings("unchecked")
		List<String> roles =  (List<String>)claims.get("roles");  
    	    	
    	//토큰에 정의된 실제DB에서 사용자의 롤정보 검색
        //UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
    	
    	JwtUserDetail userDetails =  new JwtUserDetail();
    	userDetails.setUsername(userName);
    	for(String roleValue : roles) {
    		userDetails.getRoles().add(roleValue);
    	}
    	    	
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
 
    // Request의 Header에서 token 파싱 : "X-AUTH-TOKEN: jwt토큰"
    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("X-AUTH-TOKEN");
    }
 
    // Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken, HttpServletRequest  req) {
        try {
        	Jwts.parser().setSigningKey(JwtConstants.SIGNING_KEY).parseClaimsJws(jwtToken);
            //Jws<Claims> claims = Jwts.parser().setSigningKey(JwtConstants.SIGNING_KEY).parseClaimsJws(jwtToken);            
            //return !claims.getBody().getExpiration().before(new Date(System.currentTimeMillis()));
            return true;
        } catch (ExpiredJwtException ex) {
        	logger.error("Expired JWT token : {}", ex.getMessage());
        	//토큰 유효기간 만료 처리를 위한 헤더 설정
        	//AuthenticationEntryPoint 에서 별도 처리
        	req.setAttribute("expired","Token Expired");
        } catch(MalformedJwtException  ex) {
        	logger.error("Invalid JWT token : {}", ex.getMessage());        	
        }catch(UnsupportedJwtException ex) {
        	logger.error("Unsupported JWT token : {}", ex.getMessage());        	
        }catch(IllegalArgumentException ex) {
        	logger.error("JWT Claims is Empty : {}", ex.getMessage());        	
        }
        return false;
    }
    
}
