package com.amar.productservice.repository;

import com.amar.productservice.model.Category;
import com.amar.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    List<Category> findAll();


    @Query("SELECT c FROM Category c WHERE c.name = :category")
    Category findAllByCategory(@Param("category") String category);
}