package com.example.products.repository;

import com.example.products.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartItemRepo extends JpaRepository<CartItem , Long> {

    @Modifying
    @Query(value = "DELETE c FROM cart_item c WHERE c.id = :itemId",
    nativeQuery = true)
    void deleteById( long itemId);


    @Transactional
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.cart.id = :cartId" )
    void deleteCartItemsByCartId(long cartId);



}
