package com.example.fashion.service.productservice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.fashion.domain.entity.mysqltables.Product;
import com.example.fashion.domain.entity.mysqltables.ProductVariant;
import com.example.fashion.repository.ProductColorRepository;
import com.example.fashion.repository.ProductSizeRepository;
import com.example.fashion.repository.ProductVariantRepository;

@Service
public class ProductVariantService {
    private final ProductVariantRepository productVariantRepo;
    private final ProductColorRepository productColorRepo;
    private final ProductSizeRepository productSizeRepo;
    public ProductVariantService(ProductVariantRepository productVariantRepo, ProductColorRepository productColorRepo, ProductSizeRepository productSizeRepo) {
        this.productVariantRepo = productVariantRepo;
        this.productColorRepo= productColorRepo;
        this.productSizeRepo= productSizeRepo;
    }
     public boolean isVariantExist(int colorId, int sizeId, Product product) {
        return productVariantRepo.existsByProductAndProductColor_IdAndProductSize_Id(product, colorId, sizeId);
    }

    public ProductVariant getVariantById(int variantId) {
        return productVariantRepo.findById(variantId).orElse(null);
    }

    public Set<Integer> extractProductIdFromVariants(List<ProductVariant> variants) {
        return variants.stream()
                .map(v -> v.getProduct().getId())
                .collect(Collectors.toSet());
    }
    public List<ProductVariant> findByListColor(List<Integer> colorIds) {
        return productVariantRepo.findByProductColor_IdIn(colorIds);
    } 
    public List<ProductVariant> findByListSize(List<Integer> sizeIds) {
        return productVariantRepo.findByProductSize_IdIn(sizeIds);
    }

}
