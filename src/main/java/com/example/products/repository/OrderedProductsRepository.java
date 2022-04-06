package com.example.products.repository;

import com.example.products.entity.OrderedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProducts , Long> {
    OrderedProducts findByCustomerName(String customerName);

    Optional<OrderedProducts> findByUserId(long userId);
}
