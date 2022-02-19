package com.example.products.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WishList {
    @Id
    private int id;

    private Date createdDate;

    @ManyToOne
    @JoinColumn
    private Product product;
}
