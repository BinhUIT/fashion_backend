package com.example.fashion.service.authservice;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fashion.domain.entity.mysqltables.Role;
import com.example.fashion.domain.entity.mysqltables.User;
import com.example.fashion.dto.request.LoginRequest;
import com.example.fashion.dto.request.RegisterRequest;
import com.example.fashion.dto.response.LoginResponse;
import com.example.fashion.dto.response.RegisterResponse;
import com.example.fashion.dto.response.UserInfo;
import com.example.fashion.exception.BadCredentialException;
import com.example.fashion.repository.RoleRepository;
import com.example.fashion.repository.UserRepository;
import com.example.fashion.util.Message;
import com.example.fashion.validator.UserValidator;
@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepo;
    private final AuthenticationManager authenticationManager;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepo;
    public AuthService(UserRepository userRepo, @Lazy AuthenticationManager authenticationManager, UserValidator userValidator, @Lazy PasswordEncoder passwordEncoder, RoleRepository roleRepo) {
        this.userRepo= userRepo;
        this.authenticationManager= authenticationManager;
        this.userValidator= userValidator;
        this.passwordEncoder= passwordEncoder;
        this.roleRepo= roleRepo;

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
    public RegisterResponse register(RegisterRequest request) {
        userValidator.validateRegisterRequest(request);
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Role role = roleRepo.findById(2).orElse(null);
        User user = new User(request, hashedPassword,role);
        user = userRepo.save(user);
        return new RegisterResponse(new UserInfo(user));
    }
}
