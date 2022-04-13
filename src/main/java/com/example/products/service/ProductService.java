package com.example.products.service;

import com.example.products.dto.requestDto.ProductRequest;
import com.example.products.dto.responseDto.ProductResponseDto;
import com.example.products.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();

    List<ProductResponseDto> getProductsByCompany_Id(long id);

    Optional<Product> getProductByName(String name);

    ProductResponseDto getProductById(long id);

    ProductResponseDto addProduct(ProductRequest productCompanyDto);

    ProductResponseDto updateProduct(long id, ProductRequest productCompanyDto);

    void deleteProductById(long id);




}
