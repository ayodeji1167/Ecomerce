package com.example.products.controller;

import com.example.products.dto.CartDto;
import com.example.products.dto.responseDto.CartResponseDto;
import com.example.products.entity.Cart;
import com.example.products.serviceimplementation.CartServiceImplementation;
import com.example.products.serviceimplementation.ProductServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    private final ProductServiceImplementation productServiceImplementation;
    private final CartServiceImplementation cartServiceImplementation;

    public CartController(ProductServiceImplementation productServiceImplementation, CartServiceImplementation cartServiceImplementation) {
        this.productServiceImplementation = productServiceImplementation;
        this.cartServiceImplementation = cartServiceImplementation;
    }

    @GetMapping("/addToCart")
    public ResponseEntity<CartResponseDto> addToCart(@RequestBody CartDto cartDto) {

        CartResponseDto cart = cartServiceImplementation.addToCart(cartDto);
        return new ResponseEntity<>(cart , HttpStatus.OK);


    }
}
