package com.example.products.controller;

import com.example.products.dto.requestDto.CompanyDto;
import com.example.products.dto.responseDto.CompanyResponseDto;
import com.example.products.entity.Company;
import com.example.products.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    //ADD NEW COMPANY
    @PostMapping("/add")
    public ResponseEntity<CompanyResponseDto> addNewCompany(@RequestBody CompanyDto company) {
        CompanyResponseDto company1 = companyService.addCompany(company);
        return new ResponseEntity<>(company1, HttpStatus.OK);
    }


    //GET ALL COMPANIES
    @GetMapping()
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {
        List<CompanyResponseDto> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }


    //GET COMPANY BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable long id) {
        CompanyResponseDto companyResponseDto = companyService.getCompanyById(id);
        return new ResponseEntity<>(companyResponseDto, HttpStatus.OK);
    }


    //UPDATE COMPANY
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(@PathVariable long id, @RequestBody Company company) {

        CompanyResponseDto companyResponseDto = companyService.updateCompany(id, company);

        return new ResponseEntity<>(companyResponseDto, HttpStatus.OK);
    }


    //DELETE COMPANY
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>("Company with id " + id + " deleted successfully", HttpStatus.OK);
    }

}
