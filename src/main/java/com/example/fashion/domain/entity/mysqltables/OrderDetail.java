package com.example.fashion.domain.entity.mysqltables;

import com.example.fashion.domain.embeddedkey.OrderDetailKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="ORDER_DETAILS")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailKey id;
    @ManyToOne
    @MapsId("orderId")
    private Order order;
    @ManyToOne
    @MapsId("productVariantId")
    private ProductVariant productVariant;
    public OrderDetailKey getId() {
        return id;
    }
    public void setId(OrderDetailKey id) {
        this.id = id;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public ProductVariant getProductVariant() {
        return productVariant;
    }
    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }
    public OrderDetail() {
    }
    public OrderDetail(OrderDetailKey id, Order order, ProductVariant productVariant) {
        this.id = id;
        this.order = order;
        this.productVariant = productVariant;
    }
    
}
