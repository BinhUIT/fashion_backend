package com.example.fashion.domain.selectcolumninterface;

import java.util.List;

public interface ProductInfo {
    public int getId();
    public String getName();
    public float getRating();
    public float getMinPrice();
    public float getMaxPrice();
    public int getQuantity();
    public String getImage();
    public List<String> getCategories();
}
