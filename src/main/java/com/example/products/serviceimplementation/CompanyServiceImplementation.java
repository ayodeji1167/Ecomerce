package com.example.products.serviceimplementation;

import com.example.products.entity.Company;
import com.example.products.exception.CompanyNotFoundException;
import com.example.products.repository.CompanyRepository;
import com.example.products.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImplementation implements CompanyService {

    @Autowired

    private CompanyRepository companyRepository;

    //Get all companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    //Get Company By ID
    public Optional<Company> getCompanyById(long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return company;
        } else
            throw new CompanyNotFoundException("Company with id " + id + " not found");
    }

    //Get Normal Company By Id
    public Company companyById(long id) {
        return companyRepository.getCompanyById(id);
    }

    //Update Company
    public void updateCompany(long id, Company company) {
    Optional<Company> company1 = companyRepository.findById(id);
    if (company1.isPresent()){
        companyRepository.save(company);
    }
    else
        throw new CompanyNotFoundException("Company with id " + id + " not found");
    }

    //Add Company
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public void deleteCompany(long id) {
        companyRepository.deleteById(id);
    }
}
