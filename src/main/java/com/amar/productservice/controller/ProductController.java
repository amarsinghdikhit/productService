package com.amar.productservice.controller;

import com.amar.productservice.model.Product;
import com.amar.productservice.request.ProductRequest;
import com.amar.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getSingLeProduct(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public Product addNewProduct(@RequestBody ProductRequest addProductReq){
        return productService.addProduct(addProductReq);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest req){
        return productService.updateProduct(id, req);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id")Long id, @RequestBody ProductRequest product){
        return productService.replaceProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
