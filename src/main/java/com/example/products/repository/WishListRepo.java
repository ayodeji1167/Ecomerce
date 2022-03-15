package com.example.products.repository;

import com.example.products.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepo extends JpaRepository<WishList, Long> {

}
