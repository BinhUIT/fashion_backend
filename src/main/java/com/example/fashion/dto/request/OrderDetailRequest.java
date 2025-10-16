package com.example.fashion.dto.request;

public class OrderDetailRequest {
    private int productVariantId;
    private int quantity;
    public int getProductVariantId() {
        return productVariantId;
    }
    public void setProductVariantId(int productVariantId) {
        this.productVariantId = productVariantId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public OrderDetailRequest() {
    }
    public OrderDetailRequest(int productVariantId, int quantity) {
        this.productVariantId = productVariantId;
        this.quantity = quantity;
    }
    
}
