package com.example.fashion.domain.entity.mysqltables;

import java.util.Date;

import com.example.fashion.domain.enums.EOrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id; 
    @ManyToOne 
    @JoinColumn(name = "user_id") 
    private User user;
    private Date createAt;
    private Date updateAt;
    private Date payAt;
    private EOrderStatus status;
    private float shipping_fee;
    private float total;
    private String email;
    private String address;
    private String phone;
    

}
