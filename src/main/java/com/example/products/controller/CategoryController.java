package com.example.products.controller;

import com.example.products.dto.requestDto.CategoryDto;
import com.example.products.dto.responseDto.CategoryResponseDto;
import com.example.products.entity.Category;
import com.example.products.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    //CREATE NEW CATEGORY
    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryDto category) {
        CategoryResponseDto categoryResponseDto = categoryService.createCategory(category);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);

    }

    //GET ALL CATEGORIES
    @GetMapping()
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<CategoryResponseDto> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    //GET CATEGORY BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable int id){
      CategoryResponseDto category = categoryService.getCategoryById(id);

        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    //UPDATE CATEGORY
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable int id, @RequestBody Category category){
     CategoryResponseDto categoryResponseDto = categoryService.updateCategoryById(id,category);
     return new ResponseEntity<>(categoryResponseDto,HttpStatus.OK);
    }

    //DELETE CATEGORY
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category with id " + id + " deleted Sucessfull" , HttpStatus.OK);
    }



}
