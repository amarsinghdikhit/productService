package com.amar.productservice.repository;

import com.amar.productservice.model.Category;
import com.amar.productservice.model.Product;
import com.amar.productservice.repository.projections.ProductWithIdAndTitle;
import com.amar.productservice.request.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    List<Product> findByTitleContaining(String word);

    long deleteByTitleIgnoreCase(String title);

    List<Product> findByTitleAndDescription(String title, String description);

    List<Product> findByPriceBetween(double startRange, double endRange);

    List<Product> findByCategory(Category category);

    List<Product> findByCategory_Id(long id);

    Product save(ProductRequest product);

    @Query("Select p.id as id, p.description as description from Product p where p.id= :id")
    List<ProductWithIdAndTitle>  retrieveProdcutList(@Param("id") Long id);

    @Query( value = "Select * from product p where p.id = 111",nativeQuery = true)
    List<Product> retrieveProductListByNativeQuery();

}
