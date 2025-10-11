package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.domain.entity.mysqltables.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
