package com.example.fashion.controller.authcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fashion.dto.request.LoginRequest;
import com.example.fashion.dto.request.RegisterRequest;
import com.example.fashion.dto.response.CreateNewAccessTokenResponse;
import com.example.fashion.dto.response.GlobalResponse;
import com.example.fashion.dto.response.LoginResponse;
import com.example.fashion.dto.response.RegisterResponse;
import com.example.fashion.service.authservice.AuthService;
import com.example.fashion.util.Message;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService= authService;
    } 
    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse result = authService.login(request);
        GlobalResponse<LoginResponse> response = new GlobalResponse<>(Message.successMessage, result, 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<GlobalResponse<RegisterResponse>> register(@RequestBody RegisterRequest request) {
        RegisterResponse result = authService.register(request);
        GlobalResponse<RegisterResponse> response = new GlobalResponse<>(Message.successMessage,result, 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get_new_access_token")
    public ResponseEntity<GlobalResponse<CreateNewAccessTokenResponse>> getNewAccessToken(@RequestParam String refreshToken) {
        CreateNewAccessTokenResponse result = authService.createNewAcessToken(refreshToken);
        GlobalResponse<CreateNewAccessTokenResponse> response = new GlobalResponse<>(Message.successMessage, result, 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
