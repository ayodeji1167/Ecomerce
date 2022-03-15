package com.example.products.service;

import com.example.products.entity.Product;
import com.example.products.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductsByCompany_Id(long id);

    Optional<Product> getProductByName(String name);

    Optional<Product> getProductById(long id);

    Product addProduct(ProductDto productCompanyDto);

    Product updateProduct(long id, ProductDto productCompanyDto);

    void deleteProductById(long id);




}
