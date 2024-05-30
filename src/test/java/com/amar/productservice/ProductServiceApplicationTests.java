package com.amar.productservice;

import com.amar.productservice.model.Product;
import com.amar.productservice.repository.ProductRepository;
import com.amar.productservice.repository.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    @Commit
    void testQueries(){
        List<ProductWithIdAndTitle> products = productRepository.retrieveProdcutList(111L);

        for(ProductWithIdAndTitle product: products){
            System.out.println(product.getId());
            System.out.println(product.getDescription());
        }

        List<Product> productByNativeQuery = productRepository.retrieveProductListByNativeQuery();

        for(Product product: productByNativeQuery){
            System.out.println(product.getId());
            System.out.println(product.getDescription());
        }
    }
 }
