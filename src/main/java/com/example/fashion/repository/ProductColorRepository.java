package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fashion.domain.entity.mysqltables.ProductColor;
@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {
    public List<ProductColor> findByIdIn(List<Integer> listId);
}
