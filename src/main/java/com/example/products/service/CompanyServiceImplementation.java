package com.example.products.service;

import com.example.products.data.Company;
import com.example.products.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImplementation implements CompanyService{

    @Autowired

    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(int id) {
        return companyRepository.getCompanyById(id);
    }

    public void updateCompany(int id , Company company) {
        company.setId(id);
        companyRepository.save(company);
    }

    public void addCompany(Company company) {
companyRepository.save(company);
    }

    public void deleteCompany(int id) {
companyRepository.deleteById(id);
    }
}
