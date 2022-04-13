package com.example.products.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalPrice;

    private int itemsNumber;

    @OneToMany( fetch = FetchType.LAZY ,mappedBy = "cart")
    @JsonIgnore
    private Set<CartItem> cartItems;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" , unique = true)
    @JsonIgnore
    private AppUser appUser;



}
