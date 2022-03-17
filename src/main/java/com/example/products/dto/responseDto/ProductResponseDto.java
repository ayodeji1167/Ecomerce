package com.example.products.dto.responseDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class ProductResponseDto {

    private Long id;

    private String companyName;

    private String name;

    private double price;

    private String categoryName;

}
