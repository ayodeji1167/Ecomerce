package com.example.products.repository;

import com.example.products.dto.responseDto.CartResponseDto;
import com.example.products.entity.Cart;
import com.example.products.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getCartByUser(User user);
    List<Cart> getCartsByUser(User user);
}
