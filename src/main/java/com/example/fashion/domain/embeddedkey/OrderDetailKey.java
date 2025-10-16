package com.example.fashion.domain.embeddedkey;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderDetailKey implements Serializable {
    private Integer orderId;
    private Integer productVariantId;
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getProductVariantId() {
        return productVariantId;
    }
    public void setProductVariantId(Integer productVariantId) {
        this.productVariantId = productVariantId;
    }
    public OrderDetailKey() {
    }
    public OrderDetailKey(Integer orderId, Integer productVariantId) {
        this.orderId = orderId;
        this.productVariantId = productVariantId;
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof OrderDetailKey other) {
            return Objects.equals(this.orderId, other.orderId)&&Objects.equals(this.productVariantId, other.productVariantId);
        } 
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.orderId, this.productVariantId);
    }
    

}
