package com.example.products.service;

import com.example.products.dto.requestDto.AppUserDto;
import com.example.products.dto.requestDto.ShippingAddressDto;
import com.example.products.entity.AppUser;


public interface UserService {

    AppUser createUser(AppUserDto appUserDto);

    AppUser createAdmin(AppUserDto appUserDto);

    void deleteUser(long id);

    AppUser updateUser(long id, AppUserDto user);

    AppUser findUserById(long id);

    AppUser setShippingAddress(ShippingAddressDto shippingAddressDto, long userId);
}
