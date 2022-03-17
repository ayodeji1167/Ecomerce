package com.example.products.serviceimplementation;

import com.example.products.dto.CartDto;
import com.example.products.dto.responseDto.CartResponseDto;
import com.example.products.dto.responseDto.ProductResponseDto;
import com.example.products.entity.Cart;
import com.example.products.entity.Product;
import com.example.products.exception.ProductNotFoundException;
import com.example.products.repository.CartRepository;
import com.example.products.repository.ProductRepository;
import com.example.products.repository.UserRepo;
import com.example.products.service.CartService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CartServiceImplementation implements CartService {
    private Set<Product> products = new HashSet<>();
    private final ProductRepository productRepository;
    private final CartRepository cartRepo;
    private final UserRepo userRepo;


    public CartServiceImplementation(ProductRepository productRepository, CartRepository cartRepo, UserRepo userRepo) {
        this.productRepository = productRepository;

        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
    }

    @Override
    public CartResponseDto addToCart(CartDto cartDto) {
        Product product = productRepository.findById(cartDto.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product with id" +
                " " + cartDto.getProductId() + " is invalid"));
        products.add(product);
        Cart cart = new Cart();
        cart.setProducts(products);
        cart.setUser(userRepo.findById(cartDto.getUserId()).get());
        cart.setUserId(cartDto.getUserId());
        cartRepo.save(cart);

        return convertCartToDto(cart);



    }

    @Override
    public Cart deleteFromCart(long productId) {
        return null;
    }

    private CartResponseDto convertCartToDto(Cart cart){
        CartResponseDto cartResponseDto = new CartResponseDto();
        Set<ProductResponseDto> productResponseDtos = new HashSet<>();
        for (Product product : cart.getProducts()){

            productResponseDtos.add(ProductServiceImplementation.convertToDto(product));

        }

        cartResponseDto.setProductResponseDto(productResponseDtos);

        return cartResponseDto;
    }
}
