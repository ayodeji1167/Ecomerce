package com.example.products.service;

import com.example.products.dto.CartDto;
import com.example.products.dto.responseDto.CartResponseDto;
import com.example.products.entity.Cart;

public interface CartService {
    CartResponseDto addToCart(CartDto cartDto);

    Cart deleteFromCart(long productId);

}
