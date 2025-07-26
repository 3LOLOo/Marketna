package com.example.E_Commerce_API.controller;

import com.example.E_Commerce_API.model.UserPrincipal;
import com.example.E_Commerce_API.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/admin/orders")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<?>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/orders")
    public ResponseEntity<List<?>> getUserOrders(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(orderService.getUserOrders(userPrincipal.getUser()));
    }

    @GetMapping("/checkout")
    public ResponseEntity<?> checkout(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(orderService.checkout(userPrincipal.getUser()));
    }

//    @GetMapping("/orders/{orderId}")
//    public ResponseEntity<?> getOrder(@PathVariable int orderId){
//        return ResponseEntity.ok(orderService.getOrder(orderId);
//    }
}
