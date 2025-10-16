package com.example.fashion.dto.response;

public class OrderCreationResponse {
    private int orderId;
    private String message;
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public OrderCreationResponse() {
    }
    public OrderCreationResponse(int orderId, String message) {
        this.orderId = orderId;
        this.message = message;
    }
    
}
