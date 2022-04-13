package com.example.products.service;

import com.example.products.entity.CartItem;

public interface CartItemService {
    void remove(long itemId, long cartId);

     void addToCart(long productId , long userId);

     CartItem getCartItemById(long itemId);



}