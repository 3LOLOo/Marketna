package com.example.E_Commerce_API.controller;
import com.example.E_Commerce_API.model.User;
import com.example.E_Commerce_API.service.MyUserDetailsService;
import com.example.E_Commerce_API.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @PostMapping("/register")
    public User Register(@RequestBody User user){
        return service.SaveUser(user);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody User user){
        return service.verify(user);
    }
}

