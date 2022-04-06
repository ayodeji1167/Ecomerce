package com.example.products.controller;


import com.example.products.dto.requestDto.ProductDto;
import com.example.products.dto.responseDto.ProductResponseDto;
import com.example.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    //ADD NEW PRODUCT
    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> addNewProduct(@RequestBody ProductDto productDto) {


        ProductResponseDto product = productService.addProduct(productDto);


        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    //GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable int id) {
        ProductResponseDto product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    //UPDATE PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable int id, @RequestBody ProductDto productCompanyDto) {
        ProductResponseDto p = productService.updateProduct(id, productCompanyDto);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    //GET PRODUCTS BY COMPANY ID
    @GetMapping("/company/{id}")
    public ResponseEntity<List<ProductResponseDto>> getProductsById(@PathVariable long id) {
        List<ProductResponseDto> productResponseDtos = productService.getProductsByCompany_Id(id);
        return new ResponseEntity<>(productResponseDtos, HttpStatus.OK);

    }

    //DELETE PRODUCT BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>("Product with id " + id + " successfully deleted", HttpStatus.OK);
    }


}

