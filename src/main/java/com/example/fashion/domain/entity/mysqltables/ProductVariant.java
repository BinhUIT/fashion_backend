package com.example.fashion.domain.entity.mysqltables;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;

import com.example.fashion.domain.enums.EEntityStatus;
import com.example.fashion.util.UUIDGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="PRODUCT_VARIANTS")
public class ProductVariant implements Comparable<ProductVariant> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id; 
    @ManyToOne
    @JoinColumn(name="product_id") 
    @JsonIgnore
    private Product product;
    @ManyToOne
    @JoinColumn(name="product_color_id") 
    private ProductColor productColor;
    @ManyToOne
    @JoinColumn(name="product_size_id") 
    private ProductSize productSize;
    private float price;
    private long quantity;
    private String image;
    private String name;
    private Date createAt;
    private Date updateAt;
    private EEntityStatus status;
    @Column(nullable = false, unique = true, updatable = false)
    private String code;
    @ColumnDefault("false")
    private boolean canDelete;
    @PrePersist
    public void generateCode() {
        if(this.code ==null) {
            this.code = UUIDGenerator.getRanDomUUID();
        }
    }
    @Override
    public int compareTo(ProductVariant o) {
        return Float.compare(this.id, o.price);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public ProductColor getProductColor() {
        return productColor;
    }
    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }
    public ProductSize getProductSize() {
        return productSize;
    }
    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public long getQuantity() {
        return quantity;
    }
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public Date getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    public EEntityStatus getStatus() {
        return status;
    }
    public void setStatus(EEntityStatus status) {
        this.status = status;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public boolean isCanDelete() {
        return canDelete;
    }
    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
    public ProductVariant(int id, Product product, ProductColor productColor, ProductSize productSize, float price,
            long quantity, String image, String name, Date createAt, Date updateAt, EEntityStatus status, String code,
            boolean canDelete) {
        this.id = id;
        this.product = product;
        this.productColor = productColor;
        this.productSize = productSize;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
        this.code = code;
        this.canDelete = canDelete;
    }
    public ProductVariant() {
    }
    
}
