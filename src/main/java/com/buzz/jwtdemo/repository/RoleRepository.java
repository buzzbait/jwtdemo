package com.buzz.jwtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buzz.jwtdemo.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{

}
