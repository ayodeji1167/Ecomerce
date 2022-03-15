package com.example.products.serviceimplementation;

import com.example.products.entity.Company;
import com.example.products.entity.Product;
import com.example.products.dto.ProductDto;
import com.example.products.exception.CompanyNotFoundException;
import com.example.products.exception.NameAlreadyExistException;
import com.example.products.exception.ProductNotFoundException;
import com.example.products.repository.ProductRepository;
import com.example.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CompanyServiceImplementation companyServiceImplementation;

    //Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Get all products by company id
    public List<Product> getProductsByCompany_Id(long id) {
        return productRepository.getProductsByCompany_Id(id);
    }

    //Get product by name
    public Optional<Product> getProductByName(String name) {
        Optional<Product> product = productRepository.getProductByName(name);
        product.orElseThrow(() -> new ProductNotFoundException("Product with name " + name + " Is Not Found"));
        return product;

    }

    //Get product by id
    public Optional<Product> getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        product.orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " Is Not Found"));
        return product;

    }

    //Add new product
    public Product addProduct(ProductDto productCompanyDto) {
        for (Product p : productRepository.findAll()) {
            if (p.getName().equals(productCompanyDto.getName()))
                throw new NameAlreadyExistException("Product with this name already exist!");
        }
        return productRepository.save(converterToEntity(productCompanyDto));

    }

    private Product converterToEntity(ProductDto productCompanyDto) {
        Product product1 = new Product();
        Optional<Company> company = companyServiceImplementation.getCompanyById(productCompanyDto.getCompanyId());

        //Checking if the company passed to the Dto is present , if it is present, then add the normal company

        if (company.isPresent()){
        product1.setCompany(companyServiceImplementation.companyById(productCompanyDto.getCompanyId()));
        product1.setPrice(productCompanyDto.getPrice());
        product1.setName(productCompanyDto.getName());}
        else
            throw new CompanyNotFoundException("company with id "+ productCompanyDto.getCompanyId()+ " does not exist");

        return product1;

    }

    //Update Product
    public Product updateProduct(long id, ProductDto productCompanyDto) {

        Optional<Product> product1 = productRepository.findById(id);
        product1.orElseThrow((() -> new ProductNotFoundException("Product with id " + id + " Is Not Found")));

       Product product = converterToEntity(productCompanyDto);

       product.setId(id);
      return   productRepository.save(product);

    }

    //Delete Product
    public void deleteProductById(long id) {
        productRepository.deleteById(id);

    }

}