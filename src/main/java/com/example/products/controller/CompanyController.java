package com.example.products.controller;

import com.example.products.data.Company;
import com.example.products.service.CompanyServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CompanyController {

    @Autowired
    private CompanyServiceImplementation companyServiceImplementation;

    @GetMapping("/companies")
    public List<Company> getAllCompanies(){
        return companyServiceImplementation.getAllCompanies();
    }


    @GetMapping("/companies/{id}")
    public Company getCompanyById(@PathVariable  int id){
        return companyServiceImplementation.getCompanyById(id);
    }

    @PostMapping("/companies")
    public void addNewCompany( @RequestBody Company company){
        companyServiceImplementation.addCompany(company);
    }

    @PutMapping("/companies/{id}")
    public void updateCompany(@PathVariable int id, @RequestBody Company company){
        companyServiceImplementation.updateCompany(id,company);
    }

    @DeleteMapping("/companies/{id}")
    public  void deleteCompany(@PathVariable int id){
        companyServiceImplementation.deleteCompany(id);
    }

}
