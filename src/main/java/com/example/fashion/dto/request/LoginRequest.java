package com.example.fashion.dto.request;

import io.micrometer.common.lang.NonNull;

public class LoginRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LoginRequest() {
    }
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    
}
