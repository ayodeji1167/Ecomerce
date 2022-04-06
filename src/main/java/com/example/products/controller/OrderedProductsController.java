package com.example.products.controller;

import com.example.products.entity.OrderedProducts;
import com.example.products.service.OrderedProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordered")
public class OrderedProductsController {

    private final OrderedProductsService service;

    public OrderedProductsController(OrderedProductsService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<OrderedProducts> getOrderedProducts(@PathVariable long userId) {

        return new ResponseEntity<>(service.getOrderedProducts(userId), HttpStatus.OK);
    }

}
