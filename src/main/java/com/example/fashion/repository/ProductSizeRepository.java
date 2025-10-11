package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fashion.domain.entity.mysqltables.ProductSize;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Integer> {
    public List<ProductSize> findByIdIn(List<Integer> listIds);
}
