package com.example.E_Commerce_API.repo;
import com.example.E_Commerce_API.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product>  findByCategoryNameIgnoreCase(String category);
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryNameContainingIgnoreCase(
            String NameKeyword,String descriptionKeyword,String CategoryKeyword);
}
