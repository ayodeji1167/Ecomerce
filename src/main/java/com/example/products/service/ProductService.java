package com.example.products.service;

import com.example.products.data.Category;
import com.example.products.data.Product;
import com.example.products.dto.ProductCompanyDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductsByCompany_Id(int id);

    Optional<Product> getProductByName(String name);

    Optional<Product> getProductById(int id);

    Product addProduct(ProductCompanyDto productCompanyDto);

    Product updateProduct(int id, ProductCompanyDto productCompanyDto);

    void deleteProductById(int id);




}
