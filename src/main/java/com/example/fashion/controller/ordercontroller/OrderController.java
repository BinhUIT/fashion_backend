package com.example.fashion.controller.ordercontroller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fashion.dto.request.OrderRequest;
import com.example.fashion.dto.response.GlobalResponse;
import com.example.fashion.dto.response.OrderCreationResponse;
import com.example.fashion.service.orderservice.OrderService;
import com.example.fashion.util.Message;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService= orderService;
    }
    @PostMapping("/create")
    public ResponseEntity<GlobalResponse<OrderCreationResponse>> createOrder(@RequestBody OrderRequest request, Authentication authentication) {
        String email = authentication.getName();
        OrderCreationResponse result = orderService.createOrder(email, request);
        GlobalResponse response = new GlobalResponse<>(Message.createOrderSuccess, result, 200);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
