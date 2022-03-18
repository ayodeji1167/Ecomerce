package com.example.products.service;

import com.example.products.dto.responseDto.ProductResponseDto;
import com.example.products.entity.Product;
import com.example.products.dto.requestDto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();

    List<ProductResponseDto> getProductsByCompany_Id(long id);

    Optional<Product> getProductByName(String name);

    ProductResponseDto getProductById(long id);

    ProductResponseDto addProduct(ProductDto productCompanyDto);

    ProductResponseDto updateProduct(long id, ProductDto productCompanyDto);

    void deleteProductById(long id);




}
