package com.example.products.dto.requestDto;


import lombok.Data;

@Data
public class ProductRequest {
    private Long companyId;
    private String name;
    private double price;
    private Long categoryId;
}
