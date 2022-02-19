package com.example.products.service;

import com.example.products.data.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> getAllCompanies();

    Optional<Company> getCompanyById(int id);

    Company companyById(int id);

    void updateCompany(int id, Company company );

    void addCompany(Company company);

    void  deleteCompany(int id);


}
