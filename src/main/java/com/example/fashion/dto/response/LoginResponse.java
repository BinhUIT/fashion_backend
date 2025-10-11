package com.example.fashion.dto.response;

public class LoginResponse {
    private UserInfo userInfo;
    private String accessToken;
    private String refreshToken;
    public UserInfo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public LoginResponse() {
    }
    public LoginResponse(UserInfo userInfo, String accessToken, String refreshToken) {
        this.userInfo = userInfo;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
    
}
