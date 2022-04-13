package com.example.products.serviceimplementation;

import com.example.products.dto.requestDto.AppUserDto;
import com.example.products.dto.requestDto.ShippingAddressDto;
import com.example.products.entity.AppUser;
import com.example.products.entity.Cart;
import com.example.products.entity.Role;
import com.example.products.entity.ShippingAddress;
import com.example.products.exception.AppUserException;
import com.example.products.repository.UserRepo;
import com.example.products.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImplementation implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImplementation(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    //CREATE A USER
    @Override
    public AppUser createUser(AppUserDto appUserDto) {
        AppUser appUser = createNeutralUser(appUserDto);
        appUser.setRole(Role.USER);
        return userRepo.save(appUser);
    }

    //CREATE AN ADMIN
    public AppUser createAdmin(AppUserDto appUserDto) {
        AppUser admin = createNeutralUser(appUserDto);
        admin.setRole(Role.ADMIN);

        return userRepo.save(admin);
    }


    //METHOD FOR CREATING A NEUTRAL USER
    public AppUser createNeutralUser(AppUserDto appUserDto) {
        AppUser appUser = new AppUser();
        Cart cart = new Cart();
        appUser.setUsername(appUserDto.getUsername());
        appUser.setFirstName(appUserDto.getFirstName());
        appUser.setLastName(appUserDto.getLastName());
        appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
        appUser.setEnabled(true);
        appUser.setCart(cart);
        cart.setAppUser(appUser);
        return appUser;
    }

    //DELETE USER
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    //UPDATE USER
    public AppUser updateUser(long id, AppUserDto appUserDto) {
        AppUser appUser1 = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("AppUser with id " + id + " not found"));
        appUser1.setId(id);
        appUser1.setUsername(appUserDto.getUsername());
        appUser1.setFirstName(appUserDto.getFirstName());
        appUser1.setLastName(appUserDto.getLastName());
        appUser1.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
        appUser1.setEnabled(true);
        appUser1.setRole(Role.USER);

        return userRepo.save(appUser1);
    }


    //GET USER BY ID
    public AppUser findUserById(long id) {
        return userRepo.findById(id).orElseThrow(() -> new AppUserException("User with Id "+ id +" not found"));
    }

    //SET SHIPPING ADDRESS
    public AppUser setShippingAddress(ShippingAddressDto shippingAddressDto, long userId){
        AppUser user = userRepo.findById(userId).orElseThrow(() -> new AppUserException("User with id " +userId + " not found"));
        ShippingAddress shippingAddress = convertShippingAdDto(shippingAddressDto);
        user.setShippingAddress(shippingAddress);

        return userRepo.save(user);
    }

    //CONVERT SHIPPING DTO TO ENTITY
    private ShippingAddress convertShippingAdDto(ShippingAddressDto shippingAddressDto){
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setCountry(shippingAddressDto.getCountry());
        shippingAddress.setState(shippingAddressDto.getState());
        shippingAddress.setCity(shippingAddressDto.getCity());
        shippingAddress.setZipcode(shippingAddressDto.getZipcode());
        shippingAddress.setHomeAddress(shippingAddressDto.getHomeAddress());
        return shippingAddress;
    }
}
