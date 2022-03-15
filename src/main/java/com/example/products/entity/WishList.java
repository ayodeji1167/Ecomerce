package com.example.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WishList {
    @Id
    private Long id;

    private Date createdDate;

    @ManyToOne
    @JoinColumn
    private Product product;

    @OneToOne
    @JoinColumn
    private User user;

}
