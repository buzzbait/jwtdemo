package com.buzz.jwtdemo.domain.memberrole;


import java.time.LocalDateTime;
import java.util.List;

import com.buzz.jwtdemo.domain.dto.MemberDomainDTO;

public interface CustomMemberRoleEntityRepository {
	List<MemberRoleEntity> findMemberRoleList();
	List<MemberDomainDTO.MemberWithRoleDto> viewMemberRoleList(LocalDateTime startDate,LocalDateTime endDate);
}
