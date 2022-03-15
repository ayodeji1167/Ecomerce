package com.example.products.controller;


import com.example.products.entity.Product;
import com.example.products.dto.ProductDto;
import com.example.products.exception.ProductNotFoundException;
import com.example.products.serviceimplementation.ProductServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController


public class ProductController {
    @Autowired
    private ProductServiceImplementation productServiceImplementation;


    //Get all products
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productServiceImplementation.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //Get product by ID
    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable int id) {
        Optional<Product> product = productServiceImplementation.getProductById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + "is not found");
        }
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    //Add new Product
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/products")
    public ResponseEntity<String> addNewProduct(@RequestBody ProductDto productCompanyDto) {

        productServiceImplementation.addProduct(productCompanyDto);
        return new ResponseEntity<String>("Product Created Successfully", HttpStatus.OK);
    }

    //Update Product
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody ProductDto productCompanyDto) {
        Optional<Product> product1 = productServiceImplementation.getProductById(id);
        if (product1.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " is not found");
        }
       Product p = productServiceImplementation.updateProduct(id, productCompanyDto);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    //Delete a Product by ID
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productServiceImplementation.deleteProductById(id);
    }


}

