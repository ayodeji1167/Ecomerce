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

    @GetMapping("/products")
    public List<ProductCompanyDto> getAllProducts() {
        return productServiceImplementation.getAllProducts();
    }


    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productServiceImplementation.getProductById(id);
    }

    @GetMapping("/products/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productServiceImplementation.getProductByName(name);
    }

    @PostMapping("/products")
    public  Product addNewProduct(@RequestBody ProductCompanyDto productCompanyDto){

       return productServiceImplementation.addProduct(productCompanyDto);
    }

//    @PutMapping("/products/{id}")
//    public void updateProduct(@PathVariable int id, @RequestBody Product product){
//        productServiceImplementation.updateProduct(id,product);
//    }
//
//    @DeleteMapping("/products/{id}")
//    public void deleteProduct(@PathVariable int id){
//        productServiceImplementation.deleteProductById(id);
//    }
//
//@DeleteMapping("/products")
//    public void deleteAllProduct(){
//        productServiceImplementation.deleteAllProducts();
//}


}
