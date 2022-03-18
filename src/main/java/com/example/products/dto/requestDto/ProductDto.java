package com.example.products.dto.requestDto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductDto {


    private Long companyId;
    private String name;
    private double price;
    private Long categoryId;


}
