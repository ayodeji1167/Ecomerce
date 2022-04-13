package com.example.products.service;

import com.example.products.dto.requestDto.CompanyDto;
import com.example.products.dto.responseDto.CompanyResponseDto;
import com.example.products.entity.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyResponseDto> getAllCompanies();

    CompanyResponseDto getCompanyById(long id);


    CompanyResponseDto updateCompany(long id, Company company );

    CompanyResponseDto addCompany(CompanyDto company);

    void  deleteCompany(long id);


}
