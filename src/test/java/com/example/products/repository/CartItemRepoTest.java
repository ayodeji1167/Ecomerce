package com.example.products.repository;

import com.example.products.entity.CartItem;
import com.example.products.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CartItemRepoTest {
    @Autowired
    CartItemRepo cartItemRepo;

    @Autowired
    ProductRepository productRepo;


    Product product = null;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("biscuit");
        product.setPrice(110);

        productRepo.save(product);

    }

    @Test
    void deleteById() {
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setTotalPrice(120);
        cartItem.setProduct(product);
        cartItem.setProductName("Name");
        cartItem.setProductPrice(12);
        cartItem.setProductQuantity(10);


        cartItemRepo.save(cartItem);

        System.out.println(cartItem + "when saved");

        cartItemRepo.deleteById(1L);

        CartItem cartItem2 = null;

        Optional<CartItem> cartItem1 = cartItemRepo.findById(1L);
        if (cartItem1.isPresent()) {
            cartItem2 = cartItem1.get();
        }

        System.out.println(cartItem + "after the logic");
        assertNull(cartItem2);

    }
}