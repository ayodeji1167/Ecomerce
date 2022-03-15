package com.example.products.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Company {
    @Id
    private Long id;

    private String name;

    private String description;

    @OneToMany
    private Set<Product> products;



}
