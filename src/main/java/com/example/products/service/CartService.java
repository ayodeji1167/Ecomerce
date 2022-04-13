package com.example.products.service;

import com.example.products.dto.responseDto.CartResponseDto;
import com.example.products.entity.Cart;

public interface CartService {
     CartResponseDto getCartById(long cartId);

    void checkOut(long userId) ;

}
