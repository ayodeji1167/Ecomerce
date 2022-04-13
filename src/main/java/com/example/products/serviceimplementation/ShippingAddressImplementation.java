package com.example.products.serviceimplementation;

import com.example.products.entity.ShippingAddress;
import com.example.products.repository.ShippingAddressRepository;
import com.example.products.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressImplementation {

    private final ShippingAddressRepository shippingAddressRepo;
    private final UserRepo userRepo;

    public ShippingAddressImplementation(ShippingAddressRepository shippingAddressRepo, UserRepo userRepo) {
        this.shippingAddressRepo = shippingAddressRepo;
        this.userRepo = userRepo;
    }

    public ShippingAddress createShippingAddress(ShippingAddress shippingAddress){
       return shippingAddressRepo.save(shippingAddress);
    }
}
