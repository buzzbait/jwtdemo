package com.buzz.jwtdemo.domain.memberrole;


import java.time.LocalDateTime;
import java.util.List;

import com.buzz.jwtdemo.domain.dto.MemberPojo;

public interface CustomMemberRoleEntityRepository {
	List<MemberRoleEntity> findMemberRoleList();
	List<MemberPojo.MemberWithRoleDto> viewMemberRoleList(LocalDateTime startDate,LocalDateTime endDate);
}
