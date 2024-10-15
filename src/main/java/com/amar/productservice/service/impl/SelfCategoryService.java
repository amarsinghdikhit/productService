package com.amar.productservice.service.impl;

import com.amar.productservice.model.Category;
import com.amar.productservice.model.Product;
import com.amar.productservice.repository.CategoryRepository;
import com.amar.productservice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService {


    private CategoryRepository categoryRepository;

    public SelfCategoryService(CategoryRepository repository){
        this.categoryRepository = repository;
    }


    @Override
    public List<String> getAllCategories() {
        List<Category> categoryList =categoryRepository.findAll();

        List<String> categories = new ArrayList<>();
        for(Category category : categoryList){
            categories.add(category.getName());
        }
        return categories;
    }

    @Override
    public List<Product> getProductsInCategory(String category) {
        Category category1 = categoryRepository.findAllByCategory(category);
        return category1.getProducts();
    }
}
