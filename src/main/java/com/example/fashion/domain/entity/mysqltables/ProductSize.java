package com.example.fashion.domain.entity.mysqltables;

import com.example.fashion.domain.enums.ESize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PRODUCT_SIZE")
public class ProductSize {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private int id;
    private ESize productSize;
    public ProductSize(){

    } 
    public ProductSize(int id, ESize productSize) {
        this.id = id;
        this.productSize= productSize;
    } 
    public int getId() {
        return this.id;
    } 
    public ESize getProductSize() {
        return this.productSize;
    }
}
