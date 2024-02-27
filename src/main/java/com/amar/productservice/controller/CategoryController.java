package com.amar.productservice.controller;

import com.amar.productservice.model.Category;
import com.amar.productservice.model.Product;
import com.amar.productservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    CategoryController(CategoryService service){
        this.categoryService = service;
    }

    @GetMapping
    public List<String> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{category}")
    public List<Product> getProductsInCategory(@PathVariable("category") String category){
        return categoryService.getProductsInCategory(category);
    }
}
