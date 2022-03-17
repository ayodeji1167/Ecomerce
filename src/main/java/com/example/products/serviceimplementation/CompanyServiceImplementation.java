package com.example.products.serviceimplementation;

import com.example.products.dto.responseDto.CompanyResponseDto;
import com.example.products.entity.Company;
import com.example.products.exception.CompanyNotFoundException;
import com.example.products.repository.CompanyRepository;
import com.example.products.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImplementation implements CompanyService {


    private final CompanyRepository companyRepository;

    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    //ADD NEW COMPANY
    public CompanyResponseDto addCompany(Company company) {
        companyRepository.save(company);
        return convertCompanyToDto(company);
    }

    //GET ALL COMPANIES
    public List<CompanyResponseDto> getAllCompanies() {
       List<Company> companies = companyRepository.findAll();
       List<CompanyResponseDto> companyResponseDtos = new ArrayList<>();

       for (Company company : companies){
           companyResponseDtos.add(convertCompanyToDto(company));
       }
       return companyResponseDtos;
    }

    //GET COMPANY BY ID
    public CompanyResponseDto getCompanyById(long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return convertCompanyToDto(company.get());
        } else
            throw new CompanyNotFoundException("Company with id " + id + " not found");
    }


    //UPDATE COMPANY
    public CompanyResponseDto updateCompany(long id, Company company) {
        Company company1 = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException("Company " +
                "with id "+ id + " not found"));


        company1.setId(id);
        company1.setDescription(company.getDescription());
        company1.setName(company.getName());
        company1.setProducts(company.getProducts());

        companyRepository.save(company1);
        return convertCompanyToDto(company1);


    }



    //DELETE COMPANY
    public void deleteCompany(long id) {
        companyRepository.deleteById(id);
    }




    //PRIVATE METHODS FOR EASY IMPLEMENTATION ------------------------------------------------------------
    private static CompanyResponseDto convertCompanyToDto(Company company){
        CompanyResponseDto companyResponseDto = new CompanyResponseDto();

        companyResponseDto.setId(company.getId());
        companyResponseDto.setName(company.getName());
        companyResponseDto.setDescription(company.getDescription());

        return companyResponseDto;




    }
}
