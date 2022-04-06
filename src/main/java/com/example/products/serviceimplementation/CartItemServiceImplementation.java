package com.example.products.serviceimplementation;

import com.example.products.entity.AppUser;
import com.example.products.entity.Cart;
import com.example.products.entity.CartItem;
import com.example.products.entity.Product;
import com.example.products.exception.ProductNotFoundException;
import com.example.products.exception.UserNotFoundException;
import com.example.products.repository.CartItemRepo;
import com.example.products.repository.CartRepository;
import com.example.products.repository.ProductRepository;
import com.example.products.repository.UserRepo;
import com.example.products.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CartItemServiceImplementation implements CartItemService {
    private final UserRepo userRepo;
    private final ProductRepository productRepo;
    private final CartItemRepo cartItemRepo;
    private final CartRepository cartRepo;

    public CartItemServiceImplementation(UserRepo userRepo, ProductRepository productRepo, CartItemRepo cartItemRepo, CartRepository cartRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.cartItemRepo = cartItemRepo;
        this.cartRepo = cartRepo;
    }


    //ADDING ITEM TO THE CART
    public CartItem addToCart(long userId, long productId) {
        AppUser appUser = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
        Cart cart = appUser.getCart();

        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException
                ("Product with id " + productId + " not found"));

        return addItem(cart, product);
    }

    //GET ITEM BY ID
    public CartItem getCartItemById(long id) {
        return cartItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Item with Id " + id + " noty found"));
    }

    private CartItem addItem(Cart cart, Product product) {

        Set<CartItem> cartItems = cart.getCartItems();
        if (cartItems.isEmpty())
            return saveANewCartItem(product, cart);


        for (CartItem cartItem : cartItems) {

            if (product.getId() == cartItem.getProduct().getId()) {
                cartItem.setProductQuantity(cartItem.getProductQuantity() + 1);
                cartItem.setTotalPrice(cartItem.getProductQuantity() * cartItem.getProduct().getPrice());


                //UPDATING YOUR CART
                double cartTotalPrice = cart.getTotalPrice();
                int cartTotalItems = cart.getItemsNumber();

                cart.setTotalPrice(cartTotalPrice + product.getPrice());
                cart.setItemsNumber(cartTotalItems + 1);
                cartRepo.save(cart);
                return cartItemRepo.save(cartItem);
            }
        }
        return saveANewCartItem(product, cart);
    }

    private CartItem saveANewCartItem(Product product, Cart cart) {
        CartItem cartItem = new CartItem();

        cartItem.setProductQuantity(1);
        cartItem.setProductName(product.getName());
        cartItem.setTotalPrice(product.getPrice());
        cartItem.setProduct(product);
        cartItem.setProductPrice(product.getPrice());
        cartItem.setCart(cart);

        //UPDATING THE CART ATTRIBUTES AND RESAVING IT

        double cartTotalPrice = cart.getTotalPrice();
        int cartTotalItems = cart.getItemsNumber();
        cart.setItemsNumber(cartTotalItems + 1);
        cart.setTotalPrice(cartTotalPrice + product.getPrice());


        cartRepo.save(cart);

        return cartItemRepo.save(cartItem);

    }


    //REMOVING ITEM FROM THE CART
    public String remove(long itemId) {

        CartItem cartItem = cartItemRepo.findById(itemId).orElseThrow(() ->
                new ProductNotFoundException("Cart with id " + itemId + " not found"));

        if (cartItem.getProductQuantity() == 1) {
            deleteItem(itemId);
            return "Item with id " + itemId + " successfully deleted";

        } else {
            cartItem.setProductQuantity(cartItem.getProductQuantity() - 1);
            cartItem.setTotalPrice(cartItem.getProductQuantity() * cartItem.getProduct().getPrice());
            cartItemRepo.save(cartItem);
            return "Item with id " + itemId + " successfully reduced";
        }


    }

    public void deleteItem(long itemId) {
        cartItemRepo.deleteById(itemId);
    }
}
