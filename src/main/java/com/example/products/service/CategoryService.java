package com.example.products.service;

import com.example.products.dto.requestDto.CategoryDto;
import com.example.products.dto.responseDto.CategoryResponseDto;
import com.example.products.entity.Category;

import java.util.List;

public interface CategoryService {

    public CategoryResponseDto createCategory(CategoryDto category);

    public List<CategoryResponseDto> getAllCategory();

    public CategoryResponseDto getCategoryById(long id);

    public CategoryResponseDto updateCategoryById(long id, Category category);

    public void deleteCategory(long id);
}
