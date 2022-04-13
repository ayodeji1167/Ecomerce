package com.example.products.serviceimplementation;

import com.example.products.entity.Cart;
import com.example.products.entity.CartItem;
import com.example.products.entity.Product;
import com.example.products.exception.AppUserException;
import com.example.products.exception.ProductException;
import com.example.products.repository.CartItemRepo;
import com.example.products.repository.CartRepository;
import com.example.products.repository.ProductRepository;
import com.example.products.repository.UserRepo;
import com.example.products.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
    public void addToCart(long cartId, long productId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new AppUserException("Cart with Id " + cartId + " not found"));

        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductException
                ("Product with id " + productId + " not found"));


        addItem(cart, product);
    }

    //GET ITEM BY ID
    public CartItem getCartItemById(long id) {
        return cartItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Item with Id " + id + " noty found"));
    }


    private CartItem addItem(Cart cart, Product product) {

        //GET ALL ITEMS IN THE CART
        Set<CartItem> cartItems = cart.getCartItems();
        System.out.println(cart);

        if (cartItems.isEmpty()) {
            return saveANewCartItem(product, cart);
        }


        for (CartItem cartItem : cartItems) {

            if (Objects.equals(product.getId(), cartItem.getProduct().getId())) {

                cartItem.setProductQuantity(cartItem.getProductQuantity() + 1);
                cartItem.setTotalPrice(cartItem.getProductQuantity() * cartItem.getProduct().getPrice());

                System.out.println(cartItem);


                //UPDATING YOUR CART
                double cartTotalPrice = cart.getTotalPrice();
                cart.setTotalPrice(cartTotalPrice + product.getPrice());
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
    public void remove(long cartId, long itemId) {

        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new AppUserException("Cart with Id " + cartId + " not found"));

        Set<CartItem> cartItems = cart.getCartItems();
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is Empty");
        }

        CartItem cartItem = new CartItem();

        for (CartItem cartItem1 : cartItems) {
            if (cartItem1.getId() == itemId)
                cartItem = cartItem1;
        }


        if (cartItem.getProductQuantity() == 1) {

            cart.setItemsNumber(cart.getItemsNumber() - 1);
            cart.setTotalPrice(cart.getTotalPrice() - cartItem.getTotalPrice());

            cartItemRepo.deleteById(itemId);

        }
        else {
            System.out.println("My quantity is more than 1");
            cartItem.setProductQuantity(cartItem.getProductQuantity() - 1);
            cartItem.setTotalPrice(cartItem.getProductQuantity() * cartItem.getProduct().getPrice());

            cart.setTotalPrice(cart.getTotalPrice() - cartItem.getProductPrice());

            cartItemRepo.save(cartItem);

        }


    }


}
