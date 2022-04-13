package com.example.products.serviceimplementation;

import com.example.products.dto.requestDto.CategoryDto;
import com.example.products.dto.responseDto.CategoryResponseDto;
import com.example.products.entity.Category;
import com.example.products.exception.CategoryException;
import com.example.products.repository.CategoryRepository;
import com.example.products.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //CREATE CATEGORY
    public CategoryResponseDto createCategory(CategoryDto categoryDto) {

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        categoryRepository.save(category);

        return convertCatToDto(category);
    }


    //GET ALL CATEGORIES
    public List<CategoryResponseDto> getAllCategory() {
      List<Category> categories = categoryRepository.findAll();
      List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
      for (Category category : categories){
          categoryResponseDtos.add(convertCatToDto(category));
      }
      return categoryResponseDtos;
    }

    //GET CATEGORY BY ID
    public CategoryResponseDto getCategoryById(long id) {
       Optional<Category> category1 = categoryRepository.findById(id);
       if (category1.isPresent()){
           return convertCatToDto(category1.get());
       }
       else {
           throw new CategoryException("Category with id " + id + " is not found");
       }
    }

    //UPDATE CATEGORY BY ID
    public CategoryResponseDto updateCategoryById(long id, Category category) {
        Category category1 = categoryRepository.findById(id).orElseThrow(() -> new CategoryException
                ("Category with id " + id + " is not found"));
        category1.setId(id);
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());

        categoryRepository.save(category1);

        return convertCatToDto(category1);

    }


   //DELETE CATEGORY
    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }


    //PRIVATE METHODS FOR EASY IMPLEMENTATIONS

    private static CategoryResponseDto convertCatToDto(Category category){
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setDescription(category.getDescription());

        return categoryResponseDto;
    }

}
