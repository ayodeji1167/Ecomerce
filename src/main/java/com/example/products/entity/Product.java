package com.example.products.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;



@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Company company;

    @Column(unique = true, nullable = false)
    private String name;

    private double price;

    @ManyToOne
    private Category category;




}
