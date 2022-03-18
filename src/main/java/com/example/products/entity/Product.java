package com.example.products.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(unique = true, nullable = false)
    private String name;

    private double price;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;




}
