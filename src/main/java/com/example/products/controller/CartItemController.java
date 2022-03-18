package com.example.products.controller;

import com.example.products.entity.CartItem;
import com.example.products.serviceimplementation.CartItemServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ADMIN')")
@RestController
public class CartItemController {
    private final CartItemServiceImplementation cartItemServiceImplementation;

    public CartItemController(CartItemServiceImplementation cartItemServiceImplementation) {
        this.cartItemServiceImplementation = cartItemServiceImplementation;
    }

    @PostMapping("/additem/{id}")
    public CartItem addItem(@PathVariable long id){
     return    cartItemServiceImplementation.addItem(id);

    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<?> removeItem(@PathVariable long id){

        return new ResponseEntity<>(cartItemServiceImplementation.remove(id) , HttpStatus.OK);

    }
}
