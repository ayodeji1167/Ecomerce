package com.example.products.controller;

import com.example.products.dto.responseDto.CompanyResponseDto;
import com.example.products.entity.Company;
import com.example.products.serviceimplementation.CompanyServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
public class CompanyController {

    private final CompanyServiceImplementation companyServiceImplementation;

    public CompanyController(CompanyServiceImplementation companyServiceImplementation) {
        this.companyServiceImplementation = companyServiceImplementation;
    }

    //ADD NEW COMPANY
    @PostMapping("/companies")
    public ResponseEntity<CompanyResponseDto> addNewCompany(@RequestBody Company company) {
        CompanyResponseDto company1 = companyServiceImplementation.addCompany(company);
        return new ResponseEntity<>(company1, HttpStatus.OK);
    }


    //GET ALL COMPANIES
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {
        List<CompanyResponseDto> companies = companyServiceImplementation.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }


    //GET COMPANY BY ID
    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable long id) {
        CompanyResponseDto companyResponseDto = companyServiceImplementation.getCompanyById(id);
        return new ResponseEntity<>(companyResponseDto, HttpStatus.OK);
    }


    //UPDATE COMPANY
    @PutMapping("/companies/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(@PathVariable long id, @RequestBody Company company) {

        CompanyResponseDto companyResponseDto = companyServiceImplementation.updateCompany(id, company);

        return new ResponseEntity<>(companyResponseDto, HttpStatus.OK);
    }


    //DELETE COMPANY
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        companyServiceImplementation.deleteCompany(id);
        return new ResponseEntity<>("Company with id " + id + " deleted successfully", HttpStatus.OK);
    }

}
