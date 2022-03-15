package com.example.products.serviceimplementation;

import com.example.products.entity.Category;
import com.example.products.exception.CategoryNotFoundException;
import com.example.products.repository.CategoryRepository;
import com.example.products.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //Create Category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }


    //Get all categories
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    //Get category by id
    public Optional<Category> getCategoryById(long id) {
       Optional<Category> category1 = categoryRepository.findById(id);
       if (category1.isPresent()){
           return category1;
       }
       else {
           throw new CategoryNotFoundException("Category with id " + id + " is not found");
       }
    }

    //Update Category by id
    public Category updateCategoryById(long id, Category category) {
        Optional<Category> category1 = categoryRepository.findById(id);
        if (category1.isPresent()){
            return categoryRepository.save(category);
        }
        else {
            throw new CategoryNotFoundException("Category with id " + id + " is not found");
        }
    }

    //Return true if category is present
    public boolean isCategoryPresent(long id) {
        return categoryRepository.findById(id).isPresent();
    }
}
