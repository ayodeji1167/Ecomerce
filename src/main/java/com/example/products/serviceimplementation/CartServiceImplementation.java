package com.example.products.serviceimplementation;

import com.example.products.dto.responseDto.CartResponseDto;
import com.example.products.entity.*;
import com.example.products.exception.UserNotFoundException;
import com.example.products.repository.CartItemRepo;
import com.example.products.repository.CartRepository;
import com.example.products.repository.OrderedProductsRepository;
import com.example.products.repository.UserRepo;
import com.example.products.service.CartService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImplementation implements CartService {

    private final CartRepository cartRepo;
    private final CartItemRepo cartItemRepo;
    private final UserRepo userRepo;
    private final OrderedProductsRepository orderedProductsRepo;


    public CartServiceImplementation(CartRepository cartRepo, CartItemRepo cartItemRepo, UserRepo userRepo, OrderedProductsRepository orderedProductsRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.userRepo = userRepo;
        this.orderedProductsRepo = orderedProductsRepo;
    }


    @Override
    public CartResponseDto getCartByUserId(long userId) {
        Cart cart = cartRepo.getByAppUser_Id(userId).orElseThrow(() -> new UsernameNotFoundException("Cart with userId " + userId + " not found"));

        double totalPrice = 0.0;
        int itemsNumber = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getTotalPrice();
            itemsNumber++;
        }

        cart.setTotalPrice(totalPrice);
        cart.setItemsNumber(itemsNumber);

        return convertCartToDto(cart);

    }

    private CartResponseDto convertCartToDto(Cart cart) {
        CartResponseDto cartResponseDto = new CartResponseDto();

        cartResponseDto.setCartItems(cart.getCartItems());
        cartResponseDto.setItemsNumber(cart.getItemsNumber());
        cartResponseDto.setTotalPrice(cart.getTotalPrice());
        return cartResponseDto;
    }

    @Transactional
    public void checkOut(long userId) {
        AppUser user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("Not found"));
        Cart cart = cartRepo.getByAppUser_Id(userId).orElseThrow(() -> new UserNotFoundException("Not found"));

        //Some Payment Logics

        //After Payment, Save The Products  To ORDERED PRODUCTS
        saveOrderedProducts(cart, user);

        cartItemRepo.deleteCartItemsByCart_Id(cart.getId());

        cart.setItemsNumber(0);
        cart.setTotalPrice(0);

        cartRepo.save(cart);


    }

    private void saveOrderedProducts(Cart cart, AppUser user) {
        OrderedProducts orderedProducts = new OrderedProducts();
        orderedProducts.setUser(user);
        orderedProducts.setTotalPrice(cart.getTotalPrice());
        orderedProducts.setCustomerName(user.getFirstName());
        orderedProducts.setOrderStatus(OrderStatus.SENT);

        Set<CartItem> cartItems = cart.getCartItems();
        List<Product> products = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            products.add(cartItem.getProduct());
        }
        orderedProducts.setProducts(products);

        orderedProductsRepo.save(orderedProducts);
    }
}
