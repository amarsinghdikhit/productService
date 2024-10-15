package com.amar.productservice.service.impl;

import com.amar.productservice.dto.FakeStoreProductDto;
import com.amar.productservice.model.Category;
import com.amar.productservice.model.Product;
import com.amar.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService {

    private RestTemplate restTemplate;

    @Autowired
    FakeStoreCategoryServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public List<String> getAllCategories() {
        String[] response = restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                String[].class);
        return List.of(response);
    }

    @Override
    public List<Product> getProductsInCategory(String category) {
        FakeStoreProductDto[] response = restTemplate.getForObject("https://fakestoreapi.com/products/category/"+ category, FakeStoreProductDto[].class);
        List<Product> productList = new ArrayList<>();
        if(response != null) {
            for (FakeStoreProductDto dto : response) {
                productList.add(fakeStoreProductTOProduct(dto));
            }
        }
        return productList;
    }

    private Product fakeStoreProductTOProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product  = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        return product;
    }

}
