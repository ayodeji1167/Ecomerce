package com.example.products.repository;

import com.example.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product>  getProductsByCompany_Id(long id);

   Optional<Product>  getProductByName(String name);








}
