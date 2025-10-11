package com.example.fashion.exception;

import org.springframework.security.authentication.BadCredentialsException;

import com.example.fashion.dto.response.GlobalResponse;

public class ExceptionHandler {
    public static <T> GlobalResponse<T> getResponseFromException(RuntimeException e) {
        e.printStackTrace();
        if(e instanceof NotFoundException) {
            return new GlobalResponse<>(e.getMessage(),null,404);
        }
        if(e instanceof BadRequestException) {
            return new GlobalResponse<>(e.getMessage(), null, 400);
        }
        if(e instanceof BadCredentialException) {
            return new GlobalResponse<>(e.getMessage(), null, 401);
        }
        if(e instanceof BadCredentialsException) {
            return new GlobalResponse<>(e.getMessage(), null, 401);
        }
        return new GlobalResponse<>( e.getMessage(),null,500);
    }
}
