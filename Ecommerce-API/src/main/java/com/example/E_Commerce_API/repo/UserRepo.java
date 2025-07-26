package com.example.E_Commerce_API.repo;
import com.example.E_Commerce_API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    User findByUsername(String username);
}
