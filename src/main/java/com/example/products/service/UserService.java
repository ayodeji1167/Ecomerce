package com.example.products.service;

import com.example.products.dto.requestDto.ShippingAddressDto;
import com.example.products.dto.requestDto.UserDto;
import com.example.products.entity.AppUser;
import com.example.products.entity.ShippingAddress;

import java.util.Optional;


public interface UserService {

    AppUser createUser(UserDto userDto);
    AppUser createAdmin(UserDto userDto);

    void deleteUser(long id);

    AppUser updateUser(long id, UserDto user);

    Optional<AppUser> findUserById(long id);

    AppUser setShippingAddress(ShippingAddressDto shippingAddressDto, long userId);
}
