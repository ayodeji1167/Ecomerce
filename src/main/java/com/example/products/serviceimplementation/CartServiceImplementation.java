package com.example.products.serviceimplementation;

import com.example.products.entity.Cart;
import com.example.products.repository.CartRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation  {

    private final CartRepository cartRepo;


    public CartServiceImplementation(CartRepository cartRepo) {
        this.cartRepo = cartRepo;
    }



    public Cart getCartById(long id){
        Cart cart = cartRepo.findById(id).orElseThrow(() ->new UsernameNotFoundException("Cart id with id " + id + " not found"));

        return cart;
    }
}
