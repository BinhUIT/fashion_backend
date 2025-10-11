package com.example.fashion.exception;

import com.example.fashion.dto.response.GlobalResponse;

public class ExceptionHandler {
    public static <T> GlobalResponse<T> getResponseFromException(RuntimeException e) {
        e.printStackTrace();
        if(e instanceof NotFoundException) {
            return new GlobalResponse(e.getMessage(),null,404);
        }
        if(e instanceof BadRequestException) {
            return new GlobalResponse<T>(e.getMessage(), null, 400);
        }
        return new GlobalResponse( e.getMessage(),null,500);
    }
}
