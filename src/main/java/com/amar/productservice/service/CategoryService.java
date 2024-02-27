package com.amar.productservice.service;

import com.amar.productservice.model.Product;

import java.util.List;

public interface CategoryService {

    List<String> getAllCategories();

    List<Product> getProductsInCategory(String category);
}
