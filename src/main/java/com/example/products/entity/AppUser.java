package com.example.products.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Length(min = 3, max = 25)
    @Column(unique = true)
    @NotEmpty(message = "Username must not be empty")
    private String username;

    @NotEmpty(message = "Enter Password!!")
    private String password;

    @NotEmpty(message = "Enter First Name")
    private String firstName;

    @NotEmpty(message = "Enter Last Name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id" , unique = true)
    private Cart cart;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id")
    private ShippingAddress shippingAddress;




}
