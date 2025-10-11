package com.example.fashion.dto.response;

public class RegisterResponse {
    private UserInfo userInfo;
    public RegisterResponse() {

    } 
    public RegisterResponse(UserInfo userInfo) {
        this.userInfo= userInfo;
    } 
    public UserInfo getUserInfo() {
        return this.userInfo;
    } 
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
