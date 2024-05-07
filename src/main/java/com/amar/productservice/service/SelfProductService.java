package com.amar.productservice.service;

import com.amar.productservice.exception.ProductNotFoundException;
import com.amar.productservice.model.Category;
import com.amar.productservice.model.Product;
import com.amar.productservice.repository.CategoryRepository;
import com.amar.productservice.repository.ProductRepository;
import com.amar.productservice.request.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    public  SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository =productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> optionalProduct =  productRepository.findById(id);

        if(optionalProduct.isEmpty()){

            throw new ProductNotFoundException("Product with id: "+ id +" does not exists");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product addProduct(Product req) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByName(req.getCategory().getName()));
        if(optionalCategory.isEmpty()){
            req.setCategory(categoryRepository.save(req.getCategory()));
        }else {
            req.setCategory(optionalCategory.get());
        }
        return productRepository.save(req);
    }

    @Override
    public Product updateProduct(Long id, ProductRequest request) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, ProductRequest req) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
