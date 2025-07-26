package com.example.E_Commerce_API.repo;

import com.example.E_Commerce_API.model.Order;
import com.example.E_Commerce_API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepo extends JpaRepository <Order, Integer> {
    List<Order> findByUser(User user);
}
