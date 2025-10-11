package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fashion.domain.entity.mysqltables.Product;
import com.example.fashion.domain.entity.mysqltables.ProductVariant;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {

    boolean existsByProductAndProductColor_IdAndProductSize_Id(Product product, int colorId, int sizeId);
    public List<ProductVariant> findByProductColor_IdIn(List<Integer> colorIds);
    public List<ProductVariant> findByProductSize_IdIn(List<Integer> sizeIds);
}
