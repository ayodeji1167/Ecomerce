package com.example.products.service;

import com.example.products.data.Product;
import com.example.products.dto.ProductCompanyDto;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductsByCompany_Id(int id);

    Product getProductByName(String name);

    Product getProductById(int id);

    Product addProduct(ProductCompanyDto productCompanyDto);

    void updateProduct(int id, Product product);

    void deleteProductById(int id);

    void deleteAllProducts();


}
