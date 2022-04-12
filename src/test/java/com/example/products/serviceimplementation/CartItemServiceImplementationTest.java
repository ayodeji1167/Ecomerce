package com.example.products.serviceimplementation;

import com.example.products.entity.*;
import com.example.products.repository.CartItemRepo;
import com.example.products.repository.CartRepository;
import com.example.products.repository.ProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class CartItemServiceImplementationTest {

    @MockBean
    CartItemRepo cartItemRepo;
    @MockBean
    ProductRepository productRepo;
    @MockBean
    CartRepository cartRepo;

    @Autowired
    CartItemServiceImplementation cartItemServiceImpl;

    @Test
    @DisplayName("Test that if a product is added twice, rather than repeating it, its quantity is set to two and the Item in the cart is still 1")
    void addToCart() {


        Product product1 = new Product();
        product1.setId(1L);
        product1.setPrice(100);
        product1.setCompany(new Company());
        product1.setCategory(new Category());
        product1.setName("Mango");


        Product product2 = new Product();
        product2.setId(2L);
        product2.setPrice(100);
        product2.setCompany(new Company());
        product2.setCategory(new Category());
        product2.setName("Mango2");

        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(new CartItem(100, 500, "Mango", 5, product2));


        Cart cart = new Cart();
        cart.setId(1L);
        cart.setAppUser(new AppUser());
        cart.setCartItems(cartItems);
        cart.setItemsNumber(1);

        Mockito.when(cartRepo.findById(1L)).thenReturn(Optional.of(cart));
        Mockito.when(productRepo.findById(1L)).thenReturn(Optional.of(product1));
        Mockito.when(productRepo.findById(2L)).thenReturn(Optional.of(product2));

        //WHEN....RE-ADDING PRODUCT 2 TO THE CART
        cartItemServiceImpl.addToCart(1L, 2L);

        assertEquals(1, cart.getItemsNumber());
    }


    @Test
    @DisplayName("Test that if an item quantity is more than 1, the item is reduced by 1 when")
    void remove() {

        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProductQuantity(5);
        cartItem.setProductPrice(111);
        cartItem.setProductName("mango3");
        cartItem.setTotalPrice(100);
        cartItem.setProduct(new Product());


        Cart cart = new Cart();
        cart.setId(1L);
        cart.setCartItems(Set.of(cartItem));


        Mockito.when(cartRepo.findById(1L)).thenReturn(Optional.of(cart));
        Mockito.when(cartItemRepo.findById(1L)).thenReturn(Optional.of(cartItem));

        cartItemServiceImpl.remove(1L, 1L);


        assertEquals(4, cartItem.getProductQuantity());

    }

    @Test
    @Disabled
    @DisplayName("Test that if an item quantity is 1, the item will be deleted")
    void deleteItem() {
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProductQuantity(1);
        cartItem.setProductPrice(111);
        cartItem.setCart(new Cart());
        cartItem.setProductName("mango3");
        cartItem.setTotalPrice(100);
        cartItem.setProduct(new Product());


        Cart cart = new Cart();
        cart.setId(1L);
        cart.setCartItems(Set.of(cartItem));


        Mockito.when(cartRepo.findById(1L)).thenReturn(Optional.of(cart));
        Mockito.when(cartItemRepo.findById(1L)).thenReturn(Optional.of(cartItem));

        cartItemServiceImpl.remove(1L, 1L);


        System.out.println(cartItem);

        assertEquals(0, cartItem.getProductQuantity());

    }
}