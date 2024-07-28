package com.amar.productservice.controller;

import com.amar.productservice.model.Product;
import com.amar.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void testSameProductsAsService(){
        // Arrange
        List<Product> mockProductList = new ArrayList<>();
        Product product1 = new Product();
        product1.setDescription("I Phone 15");
        mockProductList.add(product1);

        Product product2 = new Product();
        product1.setDescription("I Phone 15 pro max");
        mockProductList.add(product2);

        //act
        when(productService.getAllProducts()).thenReturn(mockProductList);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        //assert
        assertEquals(mockProductList.size(), response.getBody().size());
    }

    @Test
    void getSingleProduct() {
    }

    @Test
    void getAllProducts() {

    }

    @Test
    void addNewProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void replaceProduct() {
    }

    @Test
    void deleteProduct() {
    }
}