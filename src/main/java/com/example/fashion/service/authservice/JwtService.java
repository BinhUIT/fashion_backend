package com.example.fashion.service.authservice;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.fashion.domain.entity.mysqltables.RefreshToken;
import com.example.fashion.domain.entity.mysqltables.UnusedAcessToken;
import com.example.fashion.domain.entity.mysqltables.User;
import com.example.fashion.dto.response.CreateNewAccessTokenResponse;
import com.example.fashion.exception.BadRequestException;
import com.example.fashion.repository.RefreshTokenRepository;
import com.example.fashion.repository.UnusedAccessTokenRepository;
import com.example.fashion.repository.UserRepository;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;

@Component
public class JwtService {
    private static final String secret = Dotenv.load().get("SECRET_KEY");
    private static final int accessTokenLifeTime=1000 * 60 *15;
    private static final long refreshTokenLifeTime= 1000*60*60*24*7;
    private final UserRepository userRepo;
    private final RefreshTokenRepository refreshTokenRepo;
    private final UnusedAccessTokenRepository unusedAccessTokenRepo;
    public JwtService(UserRepository userRepo, RefreshTokenRepository refreshTokenRepo, UnusedAccessTokenRepository unusedAccessTokenRepo) {
        this.userRepo=userRepo;
        this.refreshTokenRepo= refreshTokenRepo;
        this.unusedAccessTokenRepo= unusedAccessTokenRepo;
    } 
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateAccessToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createAccessToken(claims, email);
    }
    public String generateRefreshToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createRefreshToken(claims, email);
    }
    private String createAccessToken(Map<String,Object> claims, String email) {
        return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + accessTokenLifeTime))
        .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }
    private String createRefreshToken(Map<String, Object> claims, String email) {
        String refreshToken =Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + refreshTokenLifeTime))
        .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        RefreshToken rfToken = new RefreshToken(refreshToken,extractExpiredDate(refreshToken));
        rfToken.setToken(refreshToken);
        refreshTokenRepo.save(rfToken);
        return refreshToken;
    }
    public String extractEmail(String token) {
       
        return extractClaim(token,Claims::getSubject );
    }
    public Date extractExpiredDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpire(String token) {
        return extractExpiredDate(token).before(new Date());
    }
    public boolean validateToken(String token,UserDetails userDetails) {
        if(!isAccessToken(token)) {
            return false;
        }
        if(userDetails instanceof User user) {
            String emailFromToken= extractEmail(token);
            return emailFromToken.equals(user.getEmail())&&!isTokenExpire(token);
        } 
        return false;
    }
    public Date extractIssueDate(String token) {
        return extractClaim(token, Claims::getIssuedAt);
    }
    public boolean isAccessToken(String token) {
        Date createAt = extractIssueDate(token);
        Date expireAt = extractExpiredDate(token);
        long ttl = expireAt.getTime()-createAt.getTime();
        return ttl<refreshTokenLifeTime;
    }
    public boolean validateRefreshToken(String token , UserDetails userDetails) {
        RefreshToken refreshToken = refreshTokenRepo.findByToken(token);
        if(refreshToken==null) {
            return false;
        } 
        boolean isValid = validateToken(token, userDetails);
        if(!isValid) {
             if(userDetails instanceof User user) {
            String emailFromToken= extractEmail(token);
            return emailFromToken.equals(user.getEmail())&&!isTokenExpire(token);
        } 
        }
        return isValid;
    }
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    } 
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public User findUserByToken(String token) {
        String email = extractEmail(token);
        return userRepo.findByEmail(email);
    }
    @Transactional
    public void handleLogout(String acessToken, String refreshToken)  {
        UnusedAcessToken accessToken= new UnusedAcessToken(acessToken, extractExpiredDate(acessToken));
        unusedAccessTokenRepo.save(accessToken);
        RefreshToken rfToken = refreshTokenRepo.findByToken(refreshToken);
        refreshTokenRepo.delete(rfToken);
    }

    public CreateNewAccessTokenResponse createNewAcessToken(String refreshToken) {
        String email = extractEmail(refreshToken);
        UserDetails userDetails = userRepo.findByEmail(email);
        
        if(!validateRefreshToken(refreshToken, userDetails)) {
            System.out.println("Invalid refresh token");
            throw new BadRequestException("Invalid refresh token");
        }
        String newAcessToken= generateAccessToken(email);
        return new CreateNewAccessTokenResponse(newAcessToken, extractExpiredDate(newAcessToken));
    
    
    }

}
