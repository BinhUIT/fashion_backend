package com.example.fashion.dto.request;

import java.util.List;

import com.example.fashion.domain.enums.EPaymentType;

public class OrderRequest {
    private String address;
    private String phone;
    private EPaymentType paymentType;
    private String couponCode;
    private List<OrderDetailRequest> listDetailRequest;
    public String getAddress() {
        return address;
    }
    public List<OrderDetailRequest> getListDetailRequest() {
        return this.listDetailRequest;
    }
    public void setListDetailRequest(List<OrderDetailRequest> listDetailRequest) {
        this.listDetailRequest= listDetailRequest;
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
    public OrderRequest(String address, String phone, EPaymentType paymentType, String couponCode, List<OrderDetailRequest> listDetailRequest) {
        this.address = address;
        this.phone = phone;
        this.paymentType = paymentType;
        this.couponCode = couponCode;
        this.listDetailRequest=listDetailRequest;
    }
    public OrderRequest() {
    }
    
    
}
