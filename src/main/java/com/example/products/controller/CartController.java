package com.example.products.controller;

import com.example.products.dto.responseDto.CartResponseDto;
import com.example.products.service.CartService;
import com.example.products.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDto> getCartItems(@PathVariable long userId) {
        return new ResponseEntity<>(cartService.getCartByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping("/checkout/{userId}")
    public ResponseEntity<?> checkoutCart(@PathVariable long userId) {
        cartService.checkOut(userId);
        String username = userService.findUserById(userId).get().getFirstName();
        String message = "Hello " + " your order has been placed and is on its way " + " thanks for shopping";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
