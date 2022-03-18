package com.example.products.dto.responseDto;


import com.example.products.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@RequiredArgsConstructor
public class CartResponseDto {

    private long cartId;
    private int quantity;
    private Set<ProductResponseDto> productResponseDtos;
    private User user;


}
