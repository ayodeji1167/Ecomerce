package com.example.products.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalPrice;

    private int itemsNumber;

    @JsonIgnore
    @OneToMany( fetch = FetchType.LAZY ,mappedBy = "cart")
    private Set<CartItem> cartItems;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" , unique = true)
    private AppUser appUser;

    public Cart(double totalPrice, int itemsNumber, Set<CartItem> cartItems, AppUser appUser) {
        this.totalPrice = totalPrice;
        this.itemsNumber = itemsNumber;
        this.cartItems = cartItems;
        this.appUser = appUser;
    }
}
