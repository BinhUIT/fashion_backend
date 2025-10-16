package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fashion.domain.embeddedkey.OrderDetailKey;
import com.example.fashion.domain.entity.mysqltables.OrderDetail;

@Repository
public interface  OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey>{

}
