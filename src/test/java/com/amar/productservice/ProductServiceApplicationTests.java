package com.amar.productservice;

import com.amar.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

//    public ProductServiceApplicationTests(ProductRepository productRepository){
//        this.productRepository = productRepository;
//    }

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    @Commit
    void testQueries(){
        productRepository.findByTitleContaining("amar");

        productRepository.deleteByTitleIgnoreCase("amar");
    }
 }
