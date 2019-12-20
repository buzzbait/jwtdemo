package com.buzz.jwtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buzz.jwtdemo.model.MemberRole;

public interface  MemberRoleRepository extends JpaRepository<MemberRole,Long> {

}
