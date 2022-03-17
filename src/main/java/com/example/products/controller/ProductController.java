package com.example.products.controller;


import com.example.products.dto.ProductDto;
import com.example.products.dto.responseDto.ProductResponseDto;
import com.example.products.serviceimplementation.ProductServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@PreAuthorize("hasRole('ADMIN')")
public class ProductController {

    private final ProductServiceImplementation productServiceImplementation;

    public ProductController(ProductServiceImplementation productServiceImplementation) {
        this.productServiceImplementation = productServiceImplementation;
    }

    //ADD NEW PRODUCT
    @PostMapping("/add/product")
    public ResponseEntity<ProductResponseDto> addNewProduct(@RequestBody ProductDto productDto) {

        ProductResponseDto product = productServiceImplementation.addProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    //GET ALL PRODUCTS
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productServiceImplementation.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //GET PRODUCT BY ID
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable int id) {
        ProductResponseDto product = productServiceImplementation.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    //UPDATE PRODUCT
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable int id, @RequestBody ProductDto productCompanyDto) {
        ProductResponseDto p = productServiceImplementation.updateProduct(id, productCompanyDto);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    //GET PRODUCTS BY COMPANY ID
    @GetMapping("/products/company/{id}")
    public ResponseEntity<List<ProductResponseDto>> getProductsById(@PathVariable long id) {
        List<ProductResponseDto> productResponseDtos = productServiceImplementation.getProductsByCompany_Id(id);
        return new ResponseEntity<>(productResponseDtos, HttpStatus.OK);

    }

    //DELETE PRODUCT BY ID
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productServiceImplementation.deleteProductById(id);
        return new ResponseEntity<>("Product with id " + id + " successfully deleted", HttpStatus.OK);
    }


}

