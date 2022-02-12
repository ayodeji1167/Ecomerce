package com.example.products.controller;


import com.example.products.data.Product;
import com.example.products.dto.ProductCompanyDto;
import com.example.products.service.ProductServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {
    @Autowired
    private ProductServiceImplementation productServiceImplementation;


    //Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productServiceImplementation.getAllProducts();
    }

    //Get products by ID
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productServiceImplementation.getProductById(id);
    }

    //Get products by name
    @GetMapping("/products/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productServiceImplementation.getProductByName(name);
    }

    //Get products by company ID
    @GetMapping("/productss/{companyId}")
    public List<Product> getProductsById(@PathVariable int companyId) {
        return productServiceImplementation.getProductsByCompany_Id(companyId);
    }

    //Add new Product
    @PostMapping("/products")
    public Product addNewProduct(@RequestBody ProductCompanyDto productCompanyDto) {

        return productServiceImplementation.addProduct(productCompanyDto);
    }

    //Update Product
    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody Product product) {
        productServiceImplementation.updateProduct(id, product);
    }

    //Delete a Product by Id
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productServiceImplementation.deleteProductById(id);
    }

    //Delete All products
    @DeleteMapping("/products")
    public void deleteAllProduct() {
        productServiceImplementation.deleteAllProducts();
    }


}
