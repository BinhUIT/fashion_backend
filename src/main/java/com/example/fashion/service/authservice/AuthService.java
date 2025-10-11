package com.example.fashion.service.authservice;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.fashion.domain.entity.mysqltables.User;
import com.example.fashion.dto.request.LoginRequest;
import com.example.fashion.dto.response.LoginResponse;
import com.example.fashion.dto.response.UserInfo;
import com.example.fashion.exception.BadCredentialException;
import com.example.fashion.repository.UserRepository;
import com.example.fashion.util.Message;
@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepo;
    private final AuthenticationManager authenticationManager;
    public AuthService(UserRepository userRepo, @Lazy AuthenticationManager authenticationManager) {
        this.userRepo= userRepo;
        this.authenticationManager= authenticationManager;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username);
    }
    public LoginResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if(auth.isAuthenticated()) {
            Object userPrincipal = auth.getPrincipal();
            if(userPrincipal instanceof User user) {
                return new LoginResponse(new UserInfo(user), null, null);
            }
            
        }
        throw new BadCredentialException(Message.badCredential);
    }
}
