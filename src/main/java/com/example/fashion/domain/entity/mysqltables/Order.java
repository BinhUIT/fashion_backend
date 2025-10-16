package com.example.fashion.domain.entity.mysqltables;

import java.util.Date;
import java.util.List;

import com.example.fashion.domain.enums.EOrderStatus;
import com.example.fashion.util.UUIDGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
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
    private float originPrice;
    @OneToOne(mappedBy = "order")
    private Payment payment;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderDetail> orderDetails;
    @Column(nullable = false, unique = true, updatable = false)
    private String code;
     @PrePersist
    public void generateCode() {
        if(this.code==null) {
            this.code = UUIDGenerator.getRanDomUUID();
        }
    }
    public int getId() {
        return id;
    }
    public List<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    } 
    public void setOrderDetails(List<OrderDetail> orderDetails){
        this.orderDetails= orderDetails;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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
    public Date getPayAt() {
        return payAt;
    }
    public void setPayAt(Date payAt) {
        this.payAt = payAt;
    }
    public EOrderStatus getStatus() {
        return status;
    }
    public void setStatus(EOrderStatus status) {
        this.status = status;
    }
    public float getShipping_fee() {
        return shipping_fee;
    }
    public void setShipping_fee(float shipping_fee) {
        this.shipping_fee = shipping_fee;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
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
    public Payment getPayment() {
        return payment;
    }
    public float getOriginPrice() {
        return originPrice;
    }
    public void setOriginPrice(float originPrice) {
        this.originPrice = originPrice;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public Order(int id, User user, Date createAt, Date updateAt, Date payAt, EOrderStatus status, float shipping_fee,
            float total, String email, String address, String phone, Payment payment, List<OrderDetail> orderDetails, String code, float originPrice) {
        this.id = id;
        this.user = user;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.payAt = payAt;
        this.status = status;
        this.shipping_fee = shipping_fee;
        this.total = total;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.payment = payment;
        this.orderDetails= orderDetails;
        this.code =code;
        this.originPrice=originPrice;
    }
    public Order() {
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    
    

}
