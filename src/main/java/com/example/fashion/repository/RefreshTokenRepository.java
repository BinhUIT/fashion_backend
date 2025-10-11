package com.example.fashion.repository;

import java.util.List;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.domain.entity.mysqltables.RefreshToken;

public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, Integer>{
    public RefreshToken findByToken(String token);
    public List<RefreshToken> findByExpireAtBefore(Date expireAt);
    
} 
