package com.example.fashion.securtityfilter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.fashion.domain.entity.mysqltables.UnusedAcessToken;
import com.example.fashion.repository.UnusedAccessTokenRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class UnusedAccessTokenFilter extends OncePerRequestFilter{
    private final UnusedAccessTokenRepository unusedAccessTokenRepo;
    public UnusedAccessTokenFilter(UnusedAccessTokenRepository unusedAccessTokenRepo) {
        this.unusedAccessTokenRepo= unusedAccessTokenRepo;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            UnusedAcessToken tk = unusedAccessTokenRepo.findByToken(token);
            if(tk!=null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("Unauthorized");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
