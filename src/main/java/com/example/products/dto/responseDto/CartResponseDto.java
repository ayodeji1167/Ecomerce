package com.example.products.dto.responseDto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@RequiredArgsConstructor
public class CartResponseDto {

    private Set<ProductResponseDto> productResponseDto;

}
