package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.dtos.CartItemRequestDto;
import com.example.E_Commerce_API.exceptions.ItemNotFound;
import com.example.E_Commerce_API.exceptions.ProductNotFound;
import com.example.E_Commerce_API.exceptions.ProductquantityNotEnough;
import com.example.E_Commerce_API.model.*;
import com.example.E_Commerce_API.repo.CartItemRepo;
import com.example.E_Commerce_API.repo.CartRepo;
import com.example.E_Commerce_API.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    public List<CartItem> getCartItems(int userId) {
        return cartRepo.findByUser_Id(userId).getCartItems();
    }

    @Transactional
    public String addItem(User user, CartItemRequestDto cartItemRequestDto) {
        Product product = productRepo.findById(cartItemRequestDto.getProductId()).orElseThrow(()-> new ProductNotFound("product not found"));

        if(cartItemRequestDto.getQuantity() > product.getQuantity()){
            throw new ProductquantityNotEnough("Not enough stock available");
        }

        Cart cart = cartRepo.findByUser_Id(user.getId());

        CartItemId id = new CartItemId(cart.getId(), product.getId());
        Optional<CartItem> optionalCartItem = cartItemRepo.findById(id);

        if(optionalCartItem.isPresent()){
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemRequestDto.getQuantity());
            cartItemRepo.save(cartItem);
        }
        else {
            CartItem cartItem = new CartItem(id, product ,cart , cartItemRequestDto.getQuantity());
            cartItemRepo.save(cartItem);
        }


        product.setQuantity(product.getQuantity() - cartItemRequestDto.getQuantity());
        productRepo.save(product);

        return "Item added successfully";
    }

    @Transactional
    public String updateItem(User user, CartItemRequestDto cartItemRequestDto) {
        Product product = productRepo.findById(cartItemRequestDto.getProductId()).orElseThrow(()-> new ProductNotFound("product not found"));
        Cart cart = cartRepo.findByUser_Id(user.getId());

        CartItemId id = new CartItemId(cart.getId(), product.getId());
        Optional<CartItem> optionalCartItem = cartItemRepo.findById(id);

        CartItem cartItem = cartItemRepo.findById(id)
                .orElseThrow(() -> new ItemNotFound("Item not found"));

        int diff = cartItemRequestDto.getQuantity() - cartItem.getQuantity();
        if(diff < 0)
        {
            //because its now negative, so I changed the operator (- , +)
            product.setQuantity(product.getQuantity() - diff);
        }
        else{
            if(diff > product.getQuantity()){
                throw new ProductquantityNotEnough("Not enough stock available");
            }
            product.setQuantity(product.getQuantity() - diff);
        }

        cartItem.setQuantity(cartItemRequestDto.getQuantity());

        cartItemRepo.save(cartItem);
        productRepo.save(product);

        return "Item updated successfully";
    }


    public String deleteItem(User user, int productId) {
        Cart cart = cartRepo.findByUser_Id(user.getId());
        CartItemId id = new CartItemId(cart.getId(), productId);

        CartItem cartItem = cartItemRepo.findById(id)
                .orElseThrow(() -> new ItemNotFound("Item not found"));

        Product product = cartItem.getProduct();
        product.setQuantity(product.getQuantity() + cartItem.getQuantity());
        productRepo.save(product);
        cartItemRepo.delete(cartItem);

        return "Item deleted successfully";
    }
}
