package com.example.E_Commerce_API.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "username can't be empty")
    private String username;
    @NotBlank(message = "password can't be empty")
    private String password;

    private String role;

    //inverse side of relationship, It does not create any new column It just allows bidirectional access
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Wallet wallet;

    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setUser(this);
    }
}
