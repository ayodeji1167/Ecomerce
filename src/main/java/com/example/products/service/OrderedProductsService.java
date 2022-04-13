package com.example.products.service;

import com.example.products.entity.OrderedProducts;

public interface OrderedProductsService {
    OrderedProducts getOrderedProducts(long userId);
}
