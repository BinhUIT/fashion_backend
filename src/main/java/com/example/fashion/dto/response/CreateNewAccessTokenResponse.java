package com.example.fashion.dto.response;

import java.util.Date;

public class CreateNewAccessTokenResponse {
    private String token;
    private Date expireAt;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Date getExpireAt() {
        return expireAt;
    }
    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }
    public CreateNewAccessTokenResponse() {
    }
    public CreateNewAccessTokenResponse(String token, Date expireAt) {
        this.token = token;
        this.expireAt = expireAt;
    }

}
