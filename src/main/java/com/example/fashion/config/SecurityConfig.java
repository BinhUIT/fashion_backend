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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.fashion.securtityfilter.JwtAuthFilter;
import com.example.fashion.securtityfilter.UnusedAccessTokenFilter;
import com.example.fashion.service.authservice.AuthService;
import com.example.fashion.service.authservice.JwtService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthService authService;
    private final JwtAuthFilter jwtFilter;
    private final UnusedAccessTokenFilter unusedAccessTokenFilter;
    public SecurityConfig(AuthService authService, JwtAuthFilter jwtFilter, UnusedAccessTokenFilter unusedAccessTokenFilter) {
        this.authService = authService;
        this.jwtFilter= jwtFilter;
        this.unusedAccessTokenFilter= unusedAccessTokenFilter;
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
        
        .anyRequest().authenticated()
        )
        .formLogin(Customizer.withDefaults());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(unusedAccessTokenFilter, JwtAuthFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
