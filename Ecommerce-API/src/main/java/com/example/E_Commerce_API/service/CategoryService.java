package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.exceptions.CategoryNotFound;
import com.example.E_Commerce_API.model.Category;
import com.example.E_Commerce_API.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public String addCategory(Category category)
    {
        categoryRepo.save(category);
        return "Category added successfully";
    }

    public String updateCategory(Category reqCategory) {
        Category category = categoryRepo.findById(reqCategory.getId())
                            .orElseThrow(() -> new CategoryNotFound("Category not found"));

        if(reqCategory.getName() != null)category.setName(reqCategory.getName());
        categoryRepo.save(category);
        return "Category updated successfully";
    }

    public String deleteCategory(int id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFound("Category not found"));

        categoryRepo.deleteById(id);
        return "Category updated successfully";
    }
}
