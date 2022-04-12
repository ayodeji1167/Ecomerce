package com.example.products.serviceimplementation;

import com.example.products.dto.requestDto.ProductDto;
import com.example.products.dto.responseDto.ProductResponseDto;
import com.example.products.entity.Category;
import com.example.products.entity.Company;
import com.example.products.entity.Product;
import com.example.products.exception.CategoryNotFoundException;
import com.example.products.exception.CompanyNotFoundException;
import com.example.products.exception.ProductNotFoundException;
import com.example.products.repository.CategoryRepository;
import com.example.products.repository.CompanyRepository;
import com.example.products.repository.ProductRepository;
import com.example.products.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {


    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;

    public ProductServiceImplementation(ProductRepository productRepository, CategoryRepository categoryRepository, CompanyRepository companyRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.companyRepository = companyRepository;

    }


    //ADD NEW PRODUCT
    public ProductResponseDto addProduct(ProductDto productCompanyDto) {


        Product product = productRepository.save(converterToEntity(productCompanyDto));

        return convertToDto(product);

    }

    //GET ALL PRODUCTS
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product : products) {
            productResponseDtos.add(convertToDto(product));
        }
        return productResponseDtos;
    }

    //GET PRODUCT BY ID
    public ProductResponseDto getProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " Is Not Found"));

        return convertToDto(product);
    }

    //UPDATE PRODUCT
    public ProductResponseDto updateProduct(long id, ProductDto productDto) {

        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id "
                + id + " Is Not Found"));

        Product product2 = converterToEntity(productDto);
        product2.setId(id);
        productRepository.save(product2);

        return convertToDto(product2);


    }


    //GET ALL PRODUCTS BY COMPANY ID;
    public List<ProductResponseDto> getProductsByCompany_Id(long id) {
       List<Product> products = productRepository.getProductsByCompany_Id(id);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
       for (Product product : products){
           productResponseDtos.add(convertToDto(product));
       }
       return productResponseDtos;
    }

    //Get product by name
    public Optional<Product> getProductByName(String name) {
        Optional<Product> product = productRepository.getProductByName(name);
        product.orElseThrow(() -> new ProductNotFoundException("Product with name " + name + " Is Not Found"));
        return product;

    }


    //CONVERT DTO TO ENNTITY
    private Product converterToEntity(ProductDto productDto) {
        Product product1 = new Product();

        //Checking if the Company / Category passed to the Dto is present , if it is present, then add the normal company
        Company company = companyRepository.findById(productDto.getCompanyId()).orElseThrow(() -> new CompanyNotFoundException(
                "Company with Id " + productDto.getCompanyId() + " not found"));

        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException(
                "Category with Id " + productDto.getCategoryId() + " not found"));


        product1.setCompany(company);
        product1.setPrice(productDto.getPrice());
        product1.setName(productDto.getName());
        product1.setCategory(category);


        return product1;

    }

    //CONVERT A PRODUCT TO DTO
    public static ProductResponseDto convertToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setCategoryName(product.getCategory().getName());
        productResponseDto.setCompanyName(product.getCompany().getName());
        productResponseDto.setId(product.getId());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setName(product.getName());

        return productResponseDto;

    }


    //Delete Product
    public void deleteProductById(long id) {
        productRepository.deleteById(id);

    }

}