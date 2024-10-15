package com.amar.productservice.service.impl;

import com.amar.productservice.exception.ProductNotFoundException;
import com.amar.productservice.model.Category;
import com.amar.productservice.model.Product;
import com.amar.productservice.repository.CategoryRepository;
import com.amar.productservice.repository.ProductRepository;
import com.amar.productservice.request.ProductRequest;
import com.amar.productservice.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

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
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product req) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByName(req.getCategory().getName()));
        if(optionalCategory.isEmpty()){
            categoryRepository.save(req.getCategory());
        }else {
            req.setCategory(optionalCategory.get());
            req.setCreatedAt(new Date());
        }
        return productRepository.save(req);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, ProductRequest request) {

        Optional<Product> optionalProduct = productRepository.findById(id);


        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setTitle(request.getTitle());
            product.setPrice(request.getPrice());
            Category category = new Category();
            category.setName(request.getCategory());
            product.setCategory(category);
            product.setDescription(request.getDescription());
            product.setImageUrl(request.getImage());
            product.setDeleted(request.isDeleted());

            return productRepository.save(product);
        } else {
            throw  new ProductNotFoundException("Product with id: " + id + " doesn't exists");
        }
    }

    @Override
    public Product replaceProduct(Long id, ProductRequest request) {
        Optional<Product> optionalProduct = productRepository.findById(id);


        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setTitle(request.getTitle());
            product.setPrice(request.getPrice());
            Category category = new Category();
            category.setName(request.getCategory());
            product.setCategory(category);
            product.setDescription(request.getDescription());
            product.setImageUrl(request.getImage());
            product.setDeleted(request.isDeleted());

            return productRepository.save(product);
        } else {
            throw  new ProductNotFoundException("Product with id: " + id + " doesn't exists");
        }
    }

    @Override
    public Product deleteProduct(Long id) {

        productRepository.deleteById(id);

        return new Product();
    }
}
