package com.example.products.dto.responseDto;


import com.example.products.entity.AppUser;
import com.example.products.entity.CartItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@RequiredArgsConstructor
public class CartResponseDto {
    private int itemsNumber;
    private double totalPrice;
    private Set<CartItem> cartItems;

}
