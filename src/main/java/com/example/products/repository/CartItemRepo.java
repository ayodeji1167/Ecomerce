package com.example.products.repository;

import com.example.products.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepo extends JpaRepository<CartItem , Long> {

    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.id = :itemId" )
    void deleteById(@Param("itemId") long itemId);
}
