package com.example.products.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double productPrice;

    private double totalPrice;

    private String productName;

    private long productQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    public CartItem(double productPrice, double totalPrice, String productName, long productQuantity, Product product) {
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.product = product;

    }
}
