package com.example.products.service;

import com.example.products.dto.requestDto.CartDto;
import com.example.products.dto.responseDto.CartResponseDto;
import com.example.products.entity.Cart;

public interface CartService {


    Cart deleteFromCart(long productId);

}
