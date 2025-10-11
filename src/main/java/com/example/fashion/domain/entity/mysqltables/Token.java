package com.example.fashion.domain.entity.mysqltables;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String token;
    protected Date expireAt;
    public Token(String token, Date expireAt) {
        this.token= token;
        this.expireAt=expireAt;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    public Token() {
    }
    public Token(int id, String token, Date expireAt) {
        this.id = id;
        this.token = token;
        this.expireAt = expireAt;
    }
    

}
