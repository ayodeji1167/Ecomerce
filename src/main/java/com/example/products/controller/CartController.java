package com.example.products.controller;

import com.example.products.dto.requestDto.CartDto;
import com.example.products.dto.responseDto.CartResponseDto;
import com.example.products.entity.Cart;
import com.example.products.serviceimplementation.CartServiceImplementation;
import com.example.products.serviceimplementation.ProductServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    private final CartServiceImplementation cartServiceImplementation;

    public CartController(ProductServiceImplementation productServiceImplementation, CartServiceImplementation cartServiceImplementation) {

        this.cartServiceImplementation = cartServiceImplementation;
    }

    @GetMapping("/getCart/{id}")
    public ResponseEntity<Cart> getCartItems(@PathVariable long id) {
        return new ResponseEntity<>(cartServiceImplementation.getCartById(id) , HttpStatus.OK);
    }

}
