package com.example.products.service;

import com.example.products.data.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public Category createCategory(Category category);

    public List<Category> getAllCategory();

    public Optional<Category> getCategoryById(int id);

    public Category updateCategoryById(int id, Category category);

    public boolean isCategoryPresent(int id);

}
