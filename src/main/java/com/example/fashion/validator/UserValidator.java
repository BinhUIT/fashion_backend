package com.example.fashion.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.example.fashion.dto.request.RegisterRequest;
import com.example.fashion.exception.BadRequestException;
import com.example.fashion.repository.UserRepository;
import com.example.fashion.util.Message;

@Component
public class UserValidator {
    private final UserRepository userRepo;
     private final String email_regex="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final String phone_regex = "^(03|05|07|08|09)\\\\d{8}$";
    public UserValidator(UserRepository userRepo) {
        this.userRepo= userRepo;
    }
    public boolean isEmailUsed(String email) {
        return userRepo.findByEmail(email)!=null;
    }
    public boolean validatePassword(String password) {
        return password.length()>=8;
    }
    public boolean validatePhone(String phone) {
        Pattern phonePattern = Pattern.compile(phone_regex);
        return true;
        //return phone!=null&&phonePattern.matcher(phone).matches();
    }
    public boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile(email_regex);
        return email!=null&&emailPattern.matcher(email).matches();
    }
    public void validateRegisterRequest(RegisterRequest request) {
        if(!validateEmail(request.getEmail())) {
            throw new BadRequestException(Message.invalidEmail);
        } 
        if(isEmailUsed(request.getEmail())) {
            throw new BadRequestException(Message.emailUsed);
        } 
        if(!validatePassword(request.getPassword())) {
            throw new BadRequestException(Message.invalidPassword);
        }
        if(!validatePhone(request.getPhone())) {
            throw new BadRequestException(Message.invalidPhone);
        }
    }
}
