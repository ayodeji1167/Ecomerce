package com.example.products.service;

import com.example.products.data.Product;
import com.example.products.data.WishList;

import java.util.List;

public interface WishListService {

    WishList addProduct(Product product);

   public void createWishList(WishList wishList);
}
