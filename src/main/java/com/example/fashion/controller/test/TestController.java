package com.example.fashion.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController {
    @GetMapping("/admin/test")
    public String testAdmin() {
        return "Test admin";
    }
    @GetMapping("/user/test")
    public String testUser() {
        return "Test user";
    }
    
}
