package com.example.fashion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.example.fashion.service.authservice.AuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthService authService;
    public SecurityConfig(AuthService authService) {
        this.authService = authService;
    } 
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authService); 
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(httpSecurityCsrfConfigurer->httpSecurityCsrfConfigurer.disable())
        .httpBasic(Customizer.withDefaults()) 
        .authorizeHttpRequests(auth->auth.requestMatchers("/product/**","/auth/**").permitAll()
        .requestMatchers("/user/**").hasAnyAuthority("USER","ADMIN") 
        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
        .requestMatchers("/shipper/**").hasAnyAuthority("SHIPPER")
        .anyRequest().authenticated()
        )
        .formLogin(Customizer.withDefaults());
        
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
