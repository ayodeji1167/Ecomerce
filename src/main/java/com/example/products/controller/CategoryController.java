package com.example.products.controller;

import com.example.products.dto.responseDto.CategoryResponseDto;
import com.example.products.entity.Category;
import com.example.products.serviceimplementation.CategoryServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {


    private final CategoryServiceImplementation categoryServiceImplementation;

    public CategoryController(CategoryServiceImplementation categoryServiceImplementation) {
        this.categoryServiceImplementation = categoryServiceImplementation;
    }


    //CREATE NEW CATEGORY
    @PostMapping("/add/category")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody Category category) {
        CategoryResponseDto categoryResponseDto = categoryServiceImplementation.createCategory(category);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);

    }

    //GET ALL CATEGORIES
    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<CategoryResponseDto> categories = categoryServiceImplementation.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    //GET CATEGORY BY ID
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable int id){
      CategoryResponseDto category = categoryServiceImplementation.getCategoryById(id);

        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    //UPDATE CATEGORY
    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable int id, @RequestBody Category category){
     CategoryResponseDto categoryResponseDto =categoryServiceImplementation.updateCategoryById(id,category);
     return new ResponseEntity<>(categoryResponseDto,HttpStatus.OK);
    }

    //DELETE CATEGORY
    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id){
        categoryServiceImplementation.deleteCategory(id);
        return new ResponseEntity<>("Category with id " + id + " deleted Sucessfull" , HttpStatus.OK);
    }



}
