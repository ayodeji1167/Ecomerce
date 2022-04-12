package com.example.products.controller;

import com.example.products.entity.CartItem;
import com.example.products.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }


    @PostMapping("/add/{cartId}/{productId}")
    public ResponseEntity<?> addItem(@PathVariable long cartId, @PathVariable long productId) {
        cartItemService.addToCart(cartId, productId);
        return new ResponseEntity<>("Item has been added", HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCCartItemById(@PathVariable Long id) {
        return new ResponseEntity<>(cartItemService.getCartItemById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartId}/{itemId}")
    public ResponseEntity<?> removeItem(@PathVariable long cartId, long itemId) {
        cartItemService.remove(cartId, itemId);
        return new ResponseEntity<>("Item with id " + itemId + " deleted", HttpStatus.OK);

    }
}
