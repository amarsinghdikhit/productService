package com.amar.productservice.controller;

import com.amar.productservice.commons.AuthenticateCommon;
import com.amar.productservice.model.Product;
import com.amar.productservice.request.ProductRequest;
import com.amar.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final AuthenticateCommon authenticateCommon;

    @Autowired
    public ProductController( ProductService productService, AuthenticateCommon authenticateCommon){
        this.productService = productService;
        this.authenticateCommon = authenticateCommon;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
//        if(authenticateCommon.validateToken(token) == null){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addNewProduct(@RequestBody Product addProductReq){
        return new ResponseEntity<>(productService.addProduct(addProductReq), HttpStatus.ACCEPTED);
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
