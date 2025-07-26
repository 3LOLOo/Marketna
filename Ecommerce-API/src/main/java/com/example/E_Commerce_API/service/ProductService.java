package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.model.Product;
import com.example.E_Commerce_API.repo.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public List<Product> getProducts(){
        return repo.findAll();
    }

    public String addProduct(Product product){
        repo.save(product);
        return "Product added successfully";
    }

    public String updateProduct(int id, Product reqProduct) {
        //Product p = repo.findById(id).orElseThrow(()->new EntityNotFoundException("grfg"));

        Optional<Product> optionalProduct = repo.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            if(reqProduct.getName() != null)product.setName(reqProduct.getName());
            if(reqProduct.getPrice() != null)product.setPrice(reqProduct.getPrice());
            if(reqProduct.getQuantity() != null)product.setQuantity(reqProduct.getQuantity());
            if(reqProduct.getCategory() != null)product.setCategory(reqProduct.getCategory());
            if(reqProduct.getDiscount() != null)product.setDiscount(reqProduct.getDiscount());
            if(reqProduct.getDiscountStart() != null)product.setDiscountStart(reqProduct.getDiscountStart());
            if(reqProduct.getDiscountEnd() != null)product.setDiscountEnd(reqProduct.getDiscountEnd());

            repo.save(product);
            return "Product updated successfully";
        } else {
            return "Product not found";
        }
    }

    public String deleteProduct(int id) {
        Optional<Product> optionalProduct = repo.findById(id);
        if (optionalProduct.isPresent()) {
            repo.deleteById(id);
            return "Product deleted successfully";
        } else {
            return "Product not found";
        }

    }

    public List<Product> getProductsByCategoryName(String name) {
        return repo.findByCategoryNameIgnoreCase(name);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryNameContainingIgnoreCase
        (keyword, keyword, keyword);
    }
}
