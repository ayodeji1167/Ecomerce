package com.example.products.repository;

import com.example.products.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findUserByUsername(String username);

    AppUser findAppUserByCartId(long cartId);
}
