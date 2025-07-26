package com.example.E_Commerce_API.controller;


import com.example.E_Commerce_API.model.Category;
import com.example.E_Commerce_API.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/category")
    public ResponseEntity<String> addCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/category")
    public ResponseEntity<String> updateCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.updateCategory(category));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id){
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/category")
//    public ResponseEntity<String> getCategory(){
//        return ResponseEntity.ok("workedddd");
//    }

}
