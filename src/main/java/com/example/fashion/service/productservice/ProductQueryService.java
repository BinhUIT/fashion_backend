package com.example.fashion.service.productservice;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.fashion.domain.entity.mysqltables.Category;
import com.example.fashion.domain.entity.mysqltables.Product;
import com.example.fashion.domain.entity.mysqltables.ProductVariant;
import com.example.fashion.domain.selectcolumninterface.ProductInfo;
import com.example.fashion.repository.CategoryRepository;
import com.example.fashion.repository.ProductRepository;
import com.example.fashion.repository.ProductVariantRepository;

@Service
public class ProductQueryService {
    private final ProductRepository productRepo;
    private final CategoryService categoryService;
    private final ProductVariantService productVariantService;
    public ProductQueryService(ProductRepository productRepo, CategoryService categoryService,
            ProductVariantService productVariantService) {
        this.productRepo = productRepo;
        this.categoryService = categoryService;
        this.productVariantService = productVariantService;
    }
    public Page<ProductInfo> findByCategory(Pageable pageable, int catId) {
        Category category = categoryService.findById(catId);
        if(category==null) return Page.empty();
        List<Integer> res = new ArrayList<>();
        if (category.getChildren() == null || category.getChildren().isEmpty()) {
            for (Product p : category.getProducts()) {
                res.add(p.getId());
            }
        } else {
            List<Category> listLeaf = categoryService.findLeafCategories(category);
            for (Category leafCat : listLeaf) {
                for (Product p : leafCat.getProducts()) {
                    res.add(p.getId());
                }
            }
        }
        return productRepo.findByIsEnableAndIdIn(true, res, pageable);
    }
    public Page<ProductInfo> findByPriceBetween(int from, int to, Pageable pageable) {
        return productRepo.findByIsEnableAndMinPriceBetween(true, from, to, pageable);
    }
    public Page<ProductInfo> findBySizeAndColor(List<Integer> sizeIds, List<Integer> colorIds, Pageable pageable) {
        List<ProductVariant> findBySize = new ArrayList<>();
        List<ProductVariant> findByColor = new ArrayList<>();

        if (sizeIds != null && !sizeIds.isEmpty()) {
            findBySize = productVariantService.findByListSize(sizeIds);
        }
        if (colorIds != null && !colorIds.isEmpty()) {
            findByColor = productVariantService.findByListColor(colorIds);
        }

        Set<Integer> setId = productVariantService.extractProductIdFromVariants(findBySize);
        setId.addAll(productVariantService.extractProductIdFromVariants(findByColor));

        List<Integer> listId = new ArrayList<>(setId);
        return productRepo.findByIsEnableAndIdIn(true, listId, pageable);
    }

    public Page<ProductInfo> findByListCategory(List<Long> listCategories, Pageable pageable) {
        return productRepo.findByIsEnableAndCategory_IdIn(true, listCategories, pageable);
    }
}
