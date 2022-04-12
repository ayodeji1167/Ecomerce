package com.example.products.serviceimplementation;

import com.example.products.dto.responseDto.CartResponseDto;
import com.example.products.entity.AppUser;
import com.example.products.entity.Cart;
import com.example.products.entity.CartItem;
import com.example.products.entity.Product;
import com.example.products.repository.CartRepository;
import com.example.products.repository.UserRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CartServiceImplementationTest {

    @MockBean
    CartRepository cartRepo;

    @MockBean
    UserRepo userRepo;

    @Autowired
    CartServiceImplementation cartServiceImpl;


    @Test
    @DisplayName("Test that when getting cart by  id, the items and total price is  correct")
    void getCartByUserId() {

        //GIVEN
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(new CartItem(100, 500, "Mango" ,5,new Product()));
        cartItems.add(new CartItem(50, 100, "Mango" ,2,new Product()));
        cartItems.add(new CartItem(200, 2000, "Mango" ,10,new Product()));

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setAppUser(new AppUser());
        cart.setCartItems(cartItems);

        Mockito.when(cartRepo.findById(1L)).thenReturn(Optional.of(cart));

        //WHEN
        CartResponseDto cartResponseDto = cartServiceImpl.getCartById(1L);


        //THEN
        assertEquals(2600, cartResponseDto.getTotalPrice());
        assertEquals(3, cartResponseDto.getItemsNumber());


    }

    @Test
    @DisplayName("Test That when user checks out, all items are being cleared")
    void checkOut() {

        //GIVEN
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(new CartItem(100, 500, "Mango" ,5 ,new Product()));
        cartItems.add(new CartItem(50, 100, "Mango" ,2,new Product()));
        cartItems.add(new CartItem(200, 2000, "Mango" ,10,new Product()));

        AppUser user = new AppUser();
        user.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setAppUser(user);
        cart.setCartItems(cartItems);

        Mockito.when(cartRepo.findById(1L)).thenReturn(Optional.of(cart));
        Mockito.when(userRepo.findAppUserByCartId(1L)).thenReturn(user);


        //WHEN
        cartServiceImpl.checkOut(1);


        //THEN
        assertEquals(0, cart.getItemsNumber());
    }
}