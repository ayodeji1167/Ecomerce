package com.example.products.service;

import com.example.products.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> getAllCompanies();

    Optional<Company> getCompanyById(long id);

    Company companyById(long id);

    void updateCompany(long id, Company company );

    void addCompany(Company company);

    void  deleteCompany(long id);


}
