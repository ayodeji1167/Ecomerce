package com.example.products.serviceimplementation;

import com.example.products.dto.requestDto.ShippingAddressDto;
import com.example.products.dto.requestDto.UserDto;
import com.example.products.entity.AppUser;
import com.example.products.entity.Cart;
import com.example.products.entity.Role;
import com.example.products.entity.ShippingAddress;
import com.example.products.exception.UserNotFoundException;
import com.example.products.repository.UserRepo;
import com.example.products.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    //CREATE A USER
    @Override
    public AppUser createUser(UserDto userDto) {
        AppUser appUser = createNeutralUser(userDto);
        appUser.setRole(Role.USER);
        return userRepo.save(appUser);
    }

    //CREATE AN ADMIN
    public AppUser createAdmin(UserDto userDto) {
        AppUser admin = createNeutralUser(userDto);
        admin.setRole(Role.ADMIN);

        return userRepo.save(admin);
    }



    //METHOD FOR CREATING A NEUTRAL USER
    private AppUser createNeutralUser(UserDto userDto) {
        AppUser appUser = new AppUser();
        Cart cart = new Cart();
        System.out.println(cart);
        appUser.setUsername(userDto.getUsername());
        appUser.setFirstName(userDto.getFirstName());
        appUser.setLastName(userDto.getLastName());
        appUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        appUser.setEmail(userDto.getEmail());
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
    public AppUser updateUser(long id, UserDto userDto) {
        AppUser appUser1 = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("AppUser with id " + id + " not found"));
        appUser1.setId(id);
        appUser1.setUsername(userDto.getUsername());
        appUser1.setFirstName(userDto.getFirstName());
        appUser1.setLastName(userDto.getLastName());
        appUser1.setPassword(passwordEncoder.encode(userDto.getPassword()));
        appUser1.setEnabled(true);
        appUser1.setEmail(userDto.getEmail());
        appUser1.setRole(Role.USER);

        return userRepo.save(appUser1);
    }


    //GET USER BY ID
    public Optional<AppUser> findUserById(long id) {
        Optional<AppUser> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("This user is not found");
        }
        return user;
    }

    //SET SHIPPING ADDRESS
    public AppUser setShippingAddress(ShippingAddressDto shippingAddressDto, long userId){
        AppUser user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " +userId + " not found"));
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
