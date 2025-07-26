package com.example.E_Commerce_API.repo;

import com.example.E_Commerce_API.model.CartItem;
import com.example.E_Commerce_API.model.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, CartItemId> {
}
