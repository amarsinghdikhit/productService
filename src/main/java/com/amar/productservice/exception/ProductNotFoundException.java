package com.amar.productservice.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

    private String errorMessage;

    public ProductNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
