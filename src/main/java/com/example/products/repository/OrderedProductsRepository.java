package com.example.products.repository;

import com.example.products.entity.OrderedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProducts, Long> {

    Optional<OrderedProducts> findByUserId(long userId);
}
