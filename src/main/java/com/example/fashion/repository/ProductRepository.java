package com.example.fashion.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fashion.domain.entity.mysqltables.Product;
import com.example.fashion.domain.selectcolumninterface.ProductInfo;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Page<ProductInfo> findByIsEnable(boolean enable, Pageable pageable);
    public Page<ProductInfo> findByIsEnableAndIdIn(boolean b, List<Integer> res,Pageable pageable);
    public Page<ProductInfo> findByIsEnableAndMinPriceBetween(boolean b, int from, int to, Pageable pageable);
    public Page<ProductInfo> findByIsEnableAndCategory_IdIn(boolean b, List<Long> listCategories, Pageable pageable);
    public Page<ProductInfo> findByIdIn(List<Integer> listProductId, PageRequest pageable);
}
