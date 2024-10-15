package com.amar.productservice.service;

import com.amar.productservice.model.Product;
import com.amar.productservice.request.ProductRequest;

import java.util.List;

public interface ProductService {

    Product  getSingleProduct(Long id);

    List<Product> getAllProducts();

    Product addProduct(Product req);

    Product updateProduct(Long id, ProductRequest request);

    Product replaceProduct(Long id, ProductRequest req);

    Product deleteProduct(Long id);
}
