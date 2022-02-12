package com.example.products.service;

import com.example.products.data.Product;
import com.example.products.dto.ProductCompanyDto;
import com.example.products.exception.NameAlreadyExistException;
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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product>  getProductsByCompany_Id(int id){
      return  productRepository.getProductsByCompany_Id(id);
    }


    public Product getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    public Product getProductById(int id) {
        return productRepository.getById(id);
    }

    public Product addProduct(ProductCompanyDto productCompanyDto) {
        for (Product p : productRepository.findAll()){
            if (p.getName().equals(productCompanyDto.getName()))
                throw new NameAlreadyExistException("Product with this name already exist!");
        }
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


    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
