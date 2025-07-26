package com.example.E_Commerce_API.controller;


import com.example.E_Commerce_API.dtos.CartItemRequestDto;
import com.example.E_Commerce_API.model.CartItem;
import com.example.E_Commerce_API.model.UserPrincipal;
import com.example.E_Commerce_API.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public List<CartItem> getCart(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return cartService.getCartItems(userPrincipal.getUser().getId());
    }

    @PostMapping("/cart")
    public String addItemToCart(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody CartItemRequestDto cartItemRequestDto){
        return cartService.addItem(userPrincipal.getUser(), cartItemRequestDto);
    }

    @PutMapping("/cart")
    public String updateItemQuantity(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody CartItemRequestDto cartItemRequestDto){
        return cartService.updateItem(userPrincipal.getUser(), cartItemRequestDto);
    }


    @DeleteMapping("/cart/{productId}")
    public String deleteItem(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable int productId)
    {
        return cartService.deleteItem(userPrincipal.getUser(), productId);
    }

}
