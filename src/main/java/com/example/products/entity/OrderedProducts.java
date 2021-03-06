package com.example.products.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class OrderedProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double totalPrice;

    private String customerName;

    @ManyToMany
    private List<Product> products;


    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private AppUser user;

    public OrderedProducts(double totalPrice, String customerName, List<Product> products, OrderStatus orderStatus, LocalDateTime createdDate, AppUser user) {
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.products = products;
        this.orderStatus = orderStatus;
        this.createdDate = createdDate;
        this.user = user;
    }
}
