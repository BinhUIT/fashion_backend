package com.example.fashion.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.fashion.dto.response.GlobalResponse;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(RuntimeException.class) 
    public ResponseEntity<GlobalResponse<Void>> handleException(RuntimeException e) {
        GlobalResponse<Void> response = com.example.fashion.exception.ExceptionHandler.getResponseFromException(e);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }
}
