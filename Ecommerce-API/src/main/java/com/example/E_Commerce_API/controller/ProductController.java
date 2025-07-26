package com.example.E_Commerce_API.controller;
import com.example.E_Commerce_API.model.Product;
import com.example.E_Commerce_API.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> viewProducts(){
        return service.getProducts();
    }

    @GetMapping("/products/category/{name}")
    public List<Product> getByCategory(@PathVariable String name){
        return service.getProductsByCategoryName(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Product> search(@RequestParam  String keyword){
        return service.searchProducts(keyword);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/products")
    public String addProduct(@RequestBody Product product){
        return service.addProduct(product);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product){
        return service.updateProduct(id, product);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }
}
