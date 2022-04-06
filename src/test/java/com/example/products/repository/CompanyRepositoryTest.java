package com.example.products.repository;

import com.example.products.entity.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;


    @Test
    void TestIfGetCompanyById() {

        //Given
        Company company = new Company("Trolley Big", "A big supermarket");
        companyRepository.save(company);

        //When
        var company1 = companyRepository.getCompanyById(company.getId());

        //Then
        assertEquals(company, company1);

    }
}