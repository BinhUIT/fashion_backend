package com.example.fashion.util;

import java.util.List;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.fashion.domain.entity.mysqltables.RefreshToken;
import com.example.fashion.domain.entity.mysqltables.UnusedAcessToken;
import com.example.fashion.repository.RefreshTokenRepository;
import com.example.fashion.repository.UnusedAccessTokenRepository;

@Component
public class TokenCleaner {
    private final RefreshTokenRepository refreshTokenRepo;
    private final UnusedAccessTokenRepository unusedAcessTokenRepo;
    public TokenCleaner(RefreshTokenRepository refreshTokenRepo, UnusedAccessTokenRepository unusedAccessTokenRepo) {
        this.refreshTokenRepo= refreshTokenRepo;
        this.unusedAcessTokenRepo= unusedAccessTokenRepo;
    }
    @Scheduled(fixedRate=1800000)
    public void cleanRefreshToken() {
        List<RefreshToken> listExpiredToken = refreshTokenRepo.findByExpireAtBefore(new Date());
        refreshTokenRepo.deleteAll(listExpiredToken);
    }
    @Scheduled(fixedRate=1800000)
    public void cleanUnusedAccessToken() {
        List<UnusedAcessToken> listUnusedAcessTokens= unusedAcessTokenRepo.findByExpireAtBefore(new Date());
        unusedAcessTokenRepo.deleteAll(listUnusedAcessTokens);
    }
}
