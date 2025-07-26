package com.example.E_Commerce_API.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {

    @EmbeddedId
    private OrderItemId orderItemId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Double price;
}
