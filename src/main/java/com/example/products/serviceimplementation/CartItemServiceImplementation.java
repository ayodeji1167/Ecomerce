package com.example.products.serviceimplementation;

import com.example.products.entity.Cart;
import com.example.products.entity.CartItem;
import com.example.products.entity.Product;
import com.example.products.entity.User;
import com.example.products.exception.ProductNotFoundException;
import com.example.products.repository.CartItemRepo;
import com.example.products.repository.ProductRepository;
import com.example.products.repository.UserRepo;
import com.example.products.security.AppUserDetails;
import com.example.products.service.CartItemService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImplementation implements CartItemService {
    private final UserRepo userRepo;
    private final ProductRepository productRepo;
    private final CartItemRepo cartItemRepo;

    public CartItemServiceImplementation(UserRepo userRepo, ProductRepository productRepo, CartItemRepo cartItemRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.cartItemRepo = cartItemRepo;
    }


    //ADDING ITEM TO THE CART
    public CartItem addItem(long productId) {

        //GET THE USER IN THE SECURITY CONTEXT
        AppUserDetails user = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username1 = user.getUsername();

        //USE THE USERNAME TO FIND THE APP USER CURRENTLY IN SESSION
        User user1 = userRepo.findUserByUsername(username1).orElseThrow(() -> new UsernameNotFoundException
                ("User with " + username1 + " not found"));


        //GET THE CART OF THAT USER
        Cart cart = user1.getCart();


        //GET PRODUCT FROM THE ID  PASSED IN THE HEADER
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException
                ("Product with id " + productId + " not found"));


        //GET ALL THE ITEMS IN THE USER CART
        List<CartItem> cartItems = cart.getCartItems();


        /*LOOP THROUGH THE WHOLE ITEMS IN THE CART, IF THERE IS THE
         SAME PRODUCT AS THE ONE PASSED (INCREASE THE QUANTITY OF THE ITEM IN THE
         */
        for (CartItem cartItem : cartItems) {

            if (product.getId() == cartItem.getProduct().getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItem.setPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
                cartItemRepo.save(cartItem);
                return cartItem;
            }
        }

        //IF NOT SAVE A NEW ITEM
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setProduct(product);
        cartItem.setPrice(product.getPrice());
        cartItem.setCart(cart);
        cartItemRepo.save(cartItem);

        return cartItem;
    }

    public String remove(long itemId) {


        CartItem cartItem = cartItemRepo.findById(itemId).orElseThrow(() ->
                new UsernameNotFoundException("Cart with id " + itemId + " not found"));

        if (cartItem.getQuantity() == 1) {
            deleteItem(itemId);
            return "Item with id " + itemId + " successfully deleted";

        } else {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItem.setPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
            cartItemRepo.save(cartItem);
            return "Item with id " + itemId + " successfully reduced";
        }


    }

    public void deleteItem(long itemId) {
        cartItemRepo.deleteById(itemId);
    }
}
