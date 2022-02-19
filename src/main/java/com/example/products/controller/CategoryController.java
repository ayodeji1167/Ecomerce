package com.example.products.controller;

import com.example.products.data.Category;
import com.example.products.exception.CategoryNotFoundException;
import com.example.products.serviceimplementation.CategoryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImplementation categoryServiceImplementation;


    //Creating new Category
    @PostMapping("/category")
    public ResponseEntity<String> createCategory(@RequestBody  Category category){
        categoryServiceImplementation.createCategory(category);
        return new ResponseEntity<>("Category Created Successfully" , HttpStatus.OK);

    }

    //Get all Categories
    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryServiceImplementation.getAllCategory();
        return new ResponseEntity<>( categories, HttpStatus.OK);

    }

    //Get category by id
    @GetMapping("/category/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable int id){
        Optional<Category> category = categoryServiceImplementation.getCategoryById(id);
        if (category.isEmpty()){
            throw new CategoryNotFoundException("This category is not found");
        }
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    //Update Category
    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category){
     categoryServiceImplementation.updateCategoryById(id,category);
     return new ResponseEntity<>(category,HttpStatus.OK);
    }



}
