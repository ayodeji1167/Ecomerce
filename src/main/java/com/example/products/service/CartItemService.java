package com.example.products.service;

import com.example.products.entity.CartItem;

public interface CartItemService {
    public String remove(long itemId);

    public void deleteItem(long itemId);

    public CartItem addToCart(long productId , long userId);

    public CartItem getCartItemById(long itemId);



}