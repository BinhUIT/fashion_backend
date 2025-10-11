package com.example.fashion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.domain.entity.mysqltables.UnusedAcessToken;

public interface UnusedAccessTokenRepository extends JpaRepository<UnusedAcessToken, Integer>{
    public UnusedAcessToken findByToken(String token);
    public List<UnusedAcessToken> findByExpireAtBefore(Date expireAt);
}
