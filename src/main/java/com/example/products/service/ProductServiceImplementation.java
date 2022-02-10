package com.example.products.service;

import com.example.products.data.Product;
import com.example.products.dto.ProductCompanyDto;
import com.example.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.products.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CompanyServiceImplementation companyServiceImplementation;

    public List<ProductCompanyDto> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());

    }
    private ProductCompanyDto convertToDto(Product product) {
        ProductCompanyDto dto = new ProductCompanyDto();


        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCompanyId(product.getCompany().getId());

        return dto;

    }



    public Product getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    public Product getProductById(int id) {
        return productRepository.getById(id);
    }

    public Product addProduct(ProductCompanyDto productCompanyDto) {

        return productRepository.save(converterToEntity(productCompanyDto));

    }

    private Product converterToEntity(ProductCompanyDto productCompanyDto) {
        Product product1 = new Product();

       product1.setCompany(companyServiceImplementation.getCompanyById(productCompanyDto.getCompanyId()));
        product1.setPrice(productCompanyDto.getPrice());
        product1.setName(productCompanyDto.getName());

        return product1;

    }

    public void updateProduct(int id, Product product) {
        product.setId(id);
        productRepository.save(product);

    }

    public void deleteProductById(int id) {
        productRepository.deleteById(id);

    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
