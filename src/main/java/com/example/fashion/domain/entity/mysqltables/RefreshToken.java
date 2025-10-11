package com.example.fashion.domain.entity.mysqltables;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="REFRESH_TOKEN")
public class RefreshToken extends Token {
    public RefreshToken() {
        super();
    }
    public RefreshToken(String token, Date expireAt) {
        super(token, expireAt);
    }
}
