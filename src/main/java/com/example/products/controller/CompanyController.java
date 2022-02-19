package com.example.products.controller;

import com.example.products.data.Company;
import com.example.products.exception.CategoryNotFoundException;
import com.example.products.serviceimplementation.CompanyServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CompanyController {

    @Autowired
    private CompanyServiceImplementation companyServiceImplementation;

    //Get all companies
    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyServiceImplementation.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }


    //Get Company By Company ID
    @GetMapping("/companies/{id}")
    public ResponseEntity<Optional<Company>> getCompanyById(@PathVariable int id) {
        Optional<Company> company = companyServiceImplementation.getCompanyById(id);
        if (company.isEmpty()) {
            throw new CategoryNotFoundException("Company with id " + id + " is not found");
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    //Add new Company
    @PostMapping("/companies")
    public ResponseEntity<String> addNewCompany(@RequestBody Company company) {
        companyServiceImplementation.addCompany(company);
        return new ResponseEntity<>("Company Added Successfully" , HttpStatus.OK);
    }

    //Update Product
    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id, @RequestBody Company company) {
      Optional<Company> company1 = companyServiceImplementation.getCompanyById(id);
      if (company1.isEmpty()){
          throw new CategoryNotFoundException("Company with Id " +id+ " not found");
      }
      companyServiceImplementation.updateCompany(id,company);

      return new ResponseEntity<>(company,HttpStatus.OK);
    }


    //Delete Company
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        companyServiceImplementation.deleteCompany(id);
        return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
    }

}
