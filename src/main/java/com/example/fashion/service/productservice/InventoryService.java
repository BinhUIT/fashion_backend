package com.example.fashion.service.productservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fashion.domain.entity.mysqltables.OrderDetail;
import com.example.fashion.domain.entity.mysqltables.Product;
import com.example.fashion.domain.entity.mysqltables.ProductVariant;
import com.example.fashion.repository.ProductRepository;
import com.example.fashion.repository.ProductVariantRepository;

@Service
public class InventoryService {
    private final ProductRepository productRepo;
    private final ProductVariantRepository productVariantRepo;
    public InventoryService(ProductRepository productRepo, ProductVariantRepository productVariantRepo) {
        this.productRepo= productRepo;
        this.productVariantRepo= productVariantRepo;
    }
    public void restoreInventory(List<OrderDetail> listOrderDetails) 
    {
        List<ProductVariant> listVariants = new ArrayList<>();
        for(OrderDetail detail:listOrderDetails) {
            ProductVariant pv = detail.getProductVariant();
            pv.setQuantity(pv.getQuantity()+detail.getAmount());
            Product p = pv.getProduct();
            p.setQuantity(p.getQuantity()+detail.getAmount());
            listVariants.add(pv);
        } 
        productVariantRepo.saveAll(listVariants);
    }
    public void increaseSold(List<OrderDetail> listOrderDetails) {
        List<Product> products = new ArrayList<>();
        for(OrderDetail detail: listOrderDetails) {
            Product p = detail.getProductVariant().getProduct();
            p.setSold(p.getSold()+detail.getAmount());
            products.add(p);
        } 
        productRepo.saveAll(products);
    }
    public void decreaseInventory(List<OrderDetail> listOrderDetails) {
        List<ProductVariant> listVariants = new ArrayList<>();
        for(OrderDetail detail:listOrderDetails) {
            ProductVariant pv = detail.getProductVariant();
            pv.setQuantity(pv.getQuantity()-detail.getAmount());
            Product p = pv.getProduct();
            p.setQuantity(p.getQuantity()-detail.getAmount());
            listVariants.add(pv);
        } 
        productVariantRepo.saveAll(listVariants);
        
    }

}
