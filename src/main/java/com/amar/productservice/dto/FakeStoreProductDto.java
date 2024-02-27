package com.amar.productservice.dto;

import com.amar.productservice.model.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;

    private String title;

    private Double price;

    private String category;

    private String description;

    private String image;

    private Rating rating;

}
