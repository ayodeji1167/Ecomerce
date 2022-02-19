package com.example.products.repository;

import com.example.products.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product>  getProductsByCompany_Id(int id);

   Optional<Product>  getProductByName(String name);








}
