package com.amar.productservice.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T> {

    private String status;
    private int code;
    private String message;
    private T data;
}
