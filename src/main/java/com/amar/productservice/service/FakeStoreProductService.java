package com.amar.productservice.service;

import com.amar.productservice.dto.FakeStoreProductDto;
import com.amar.productservice.model.Category;
import com.amar.productservice.model.Product;
import com.amar.productservice.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/1",
                FakeStoreProductDto.class);
        return fakeStoreProductTOProduct(fakeStoreProductDto);
    }

    //Concept of type erasure
    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> productList = new ArrayList<>();
        if(response != null) {
            for (FakeStoreProductDto dto : response) {
                productList.add(fakeStoreProductTOProduct(dto));
            }
        }
        return productList;
    }

    @Override
    public Product addProduct(Product req) {
        return null;
    }

//    @Override
//    public Product addProduct(ProductRequest req) {
//        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products", req, FakeStoreProductDto.class);
//        return fakeStoreProductTOProduct(fakeStoreProductDto);
//    }

    @Override
    public Product updateProduct(Long id, ProductRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductRequest> requestEntity = new HttpEntity<>(request, headers);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestEntity, ProductRequest.class);

        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(
                FakeStoreProductDto.class, restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/"+ id, HttpMethod.PATCH, requestCallback, responseExtractor);
        assert response != null;
        return fakeStoreProductTOProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, ProductRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductRequest> requestEntity = new HttpEntity<>(request, headers);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestEntity,
                ProductRequest.class);

        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(
                FakeStoreProductDto.class, restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/"+ id,
                HttpMethod.PUT, requestCallback, responseExtractor);
        return fakeStoreProductTOProduct(response);
    }

    @Override
    public Product deleteProduct(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductRequest> requestEntity = new HttpEntity<>(null, headers);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestEntity,
                ProductRequest.class);

        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(
                FakeStoreProductDto.class, restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/"+ id,
                HttpMethod.DELETE, requestCallback, responseExtractor);
        return fakeStoreProductTOProduct(response);
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
