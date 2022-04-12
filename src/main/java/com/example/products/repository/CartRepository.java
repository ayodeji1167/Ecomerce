package com.example.products.repository;

import com.example.products.entity.AppUser;
import com.example.products.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c where c.appUser.id  = :id ")
    Optional<Cart> getByAppUser_Id(long id);

}
