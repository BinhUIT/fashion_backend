package com.example.fashion.service.productservice;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.fashion.domain.entity.mysqltables.Product;
import com.example.fashion.domain.selectcolumninterface.ProductInfo;
import com.example.fashion.exception.NotFoundException;
import com.example.fashion.repository.ProductRepository;
import com.example.fashion.util.Message;

@Service
public class ProductService {
    private final ProductRepository productRepo;
    public ProductService(ProductRepository productRepo) {
        this.productRepo= productRepo;
    }
    public Page<ProductInfo> getProducts(int pageNumber, int size) {
        Pageable pageable= PageRequest.of(pageNumber, size);
        return productRepo.findByIsEnable(true, pageable);
    }
    public Product getProductDetail(int productId) {
        Product product = productRepo.findById(productId).orElse(null);
        if(product==null) {
            throw new NotFoundException(Message.productNotFound);
        }
        return product;
    }
    public Page<ProductInfo> findByListIds(List<Integer> listProductId, PageRequest pageable) {
       return productRepo.findByIdIn(listProductId, pageable);
    }
    public Page<ProductInfo> findAllEnableProductsByPage(PageRequest pageable) {
        return productRepo.findByIsEnable(true, pageable);
        
    }
}
