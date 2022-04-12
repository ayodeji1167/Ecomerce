package com.example.products.service;

import com.example.products.dto.requestDto.ShippingAddressDto;
import com.example.products.dto.requestDto.UserDto;
import com.example.products.entity.AppUser;


public interface UserService {

    AppUser createUser(UserDto userDto);
    AppUser createAdmin(UserDto userDto);

    void deleteUser(long id);

    AppUser updateUser(long id, UserDto user);

    AppUser findUserById(long id);

    AppUser setShippingAddress(ShippingAddressDto shippingAddressDto, long userId);
}
