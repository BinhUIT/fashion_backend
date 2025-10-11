package com.example.fashion.dto.request;

import io.micrometer.common.lang.NonNull;

public class RegisterRequest {
    @NonNull
    private String email;
    @NonNull
    private String name;
    @NonNull
    private String phone;
    @NonNull
    private String address;
    @NonNull
    private String password;
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public RegisterRequest() {
    }
    public RegisterRequest(String email, String name, String phone, String address, String password) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.password= password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
