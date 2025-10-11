package com.example.fashion.service.productservice;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fashion.domain.entity.mysqltables.Category;
import com.example.fashion.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;
    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo= categoryRepo;
    }
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    } 
    public Category findById(int catId) {
        return categoryRepo.findById(catId).orElse(null);
    }
    public List<Category> findLeafCategories(Category category) {
        List<Category> res= new ArrayList<>();
        dfs(category,res);
        return res;
    }
    private void dfs(Category category, List<Category> res) { 
        if(category.getChildren()==null||category.getChildren().isEmpty()) {
                return;
            }
        for(Category cat: category.getChildren()) {
            if(cat.getChildren()==null||cat.getChildren().isEmpty()) {
                res.add(cat);
            }
            else {
                dfs(cat,res);
            }
        }
    }
}
