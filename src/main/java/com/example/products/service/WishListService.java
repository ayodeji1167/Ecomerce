package com.example.products.service;

import com.example.products.entity.Product;
import com.example.products.entity.WishList;

public interface WishListService {

    WishList addProduct(Product product);

   public void createWishList(WishList wishList);
}
