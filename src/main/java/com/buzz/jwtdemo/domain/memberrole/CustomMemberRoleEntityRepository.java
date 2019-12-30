package com.buzz.jwtdemo.domain.memberrole;


import java.time.LocalDateTime;
import java.util.List;

import com.buzz.jwtdemo.domain.dto.MemberDtoDomain;

public interface CustomMemberRoleEntityRepository {
	List<MemberRoleEntity> findMemberRoleList();
	List<MemberDtoDomain.MemberWithRoleDto> viewMemberRoleList(LocalDateTime startDate,LocalDateTime endDate);
}
