package com.example.E_Commerce_API.repo;
import com.example.E_Commerce_API.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
    Cart findByUser_Id(int userId);
}
