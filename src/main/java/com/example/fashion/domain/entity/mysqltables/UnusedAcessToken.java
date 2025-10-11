package com.example.fashion.domain.entity.mysqltables;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="UNUSED_ACCESS_TOKEN")
public class UnusedAcessToken extends Token {
    public UnusedAcessToken(String token, Date expireAt){
        super(token, expireAt);
    }
    public UnusedAcessToken() {
        super();
    }
}
