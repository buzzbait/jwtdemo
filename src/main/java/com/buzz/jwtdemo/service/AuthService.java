package com.buzz.jwtdemo.service;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.buzz.jwtdemo.common.ResponseConstants;
import com.buzz.jwtdemo.domain.member.MemberEntity;
import com.buzz.jwtdemo.domain.member.MemberEntityRepository;
import com.buzz.jwtdemo.domain.memberrole.MemberRoleEntity;
import com.buzz.jwtdemo.domain.memberrole.MemberRoleEntityRepository;
import com.buzz.jwtdemo.security.JwtTokenProvider;

@Service
public class AuthService {
	
	private static Logger _logger = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	private JwtTokenProvider _jwtTokenProvider; 
	
	@Autowired
	private MemberEntityRepository _memberRepository;
	
	@Autowired
	private MemberRoleEntityRepository _memberRoleRepository;
	
	public HashMap<String,Object> signin(String userId,String userPass){
		HashMap<String,Object> result =  new HashMap<String,Object>();
		//사용자 검증 진행
		//사용자가 인증되면 인증키를 발급한다.
				
		//DB검색
		Optional<MemberEntity> optMemberEntity = _memberRepository.findByLoginId(userId);
		if(optMemberEntity.isPresent()) {
			//멤버가 존재 하는 경우
			MemberEntity loinMemeber = optMemberEntity.get();
						
			_logger.debug("사용자 검증됨.........");
						
						
			//권한 검색(N건)			
			List<MemberRoleEntity> memberRoleEntityList =  _memberRoleRepository.findByMember(loinMemeber);
			//List<MemberRole> memberRoleList =  _memberRoleRepository.findByMemberId(memerId);			
			//List<MemberRole> memberRoleList =  _memberRoleRepository.findByMemberLoginId(userId);
			
			//Spring Security 는 권한인증에 "ROLE_" 이 붙는다.
			List<String> roleList = memberRoleEntityList.stream()
									.map(v -> "ROLE_" + v.getRoleName().toString())
									.collect(Collectors.toList());			
									
			String tokenValue = this._jwtTokenProvider.createToken(loinMemeber.getLoginId(),roleList);
			result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_SUCCESS);
			result.put("tokenValue", tokenValue);
		}else {
			//멤버가 없는 경우
			result.put(ResponseConstants.STATUS, ResponseConstants.RESULT_ERROR);
			result.put(ResponseConstants.MESSAGE, "사용자 정보가 없습니다.");
		}
				
		
		return result;
	}
}
