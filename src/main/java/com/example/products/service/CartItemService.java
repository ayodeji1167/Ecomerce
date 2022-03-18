package com.example.products.service;

import com.example.products.entity.CartItem;

public interface CartItemService {
    public CartItem addItem(long productId);
}