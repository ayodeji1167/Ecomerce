package com.example.products.service;

import com.example.products.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public Category createCategory(Category category);

    public List<Category> getAllCategory();

    public Optional<Category> getCategoryById(long id);

    public Category updateCategoryById(long id, Category category);

    public boolean isCategoryPresent(long id);

}
