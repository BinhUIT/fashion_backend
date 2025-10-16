package com.example.fashion.dto.request;

import com.example.fashion.domain.enums.EPaymentType;

public class OrderRequest {
    private String address;
    private String phone;
    private EPaymentType paymentType;
    private String couponCode;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public EPaymentType getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(EPaymentType paymentType) {
        this.paymentType = paymentType;
    }
    public String getCouponCode() {
        return couponCode;
    }
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
    public OrderRequest(String address, String phone, EPaymentType paymentType, String couponCode) {
        this.address = address;
        this.phone = phone;
        this.paymentType = paymentType;
        this.couponCode = couponCode;
    }
    public OrderRequest() {
    }
    
    
}
