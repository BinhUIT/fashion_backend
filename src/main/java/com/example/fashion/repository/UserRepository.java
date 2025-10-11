package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.domain.entity.mysqltables.User;


public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    public User findById(long id);
}
