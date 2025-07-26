package com.example.E_Commerce_API.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {

    @EmbeddedId
    private CartItemId cartItemId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id",nullable = false)
    private Cart cart;

    @Column(nullable = false)
    private int quantity;
}
