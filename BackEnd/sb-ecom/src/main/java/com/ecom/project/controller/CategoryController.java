package com.ecom.project.controller;

import com.ecom.project.model.Category;
import com.ecom.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;



    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories,HttpStatus.OK);
    }


    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category)
    {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added Successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId)
    {
       try
       {
           String status =  categoryService.deleteCategory(categoryId);
           return new ResponseEntity<>(status, HttpStatus.OK);
       }catch (ResponseStatusException ex){
           return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
       }
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId)
    {
        try{
            Category categorySaved =  categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("category with id " + categoryId + "updated ", HttpStatus.OK);
        }catch(ResponseStatusException ex){
            return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());

        }
    }


}
