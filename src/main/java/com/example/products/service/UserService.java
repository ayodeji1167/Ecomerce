package com.example.products.service;

import com.example.products.dto.UserDto;
import com.example.products.entity.User;

import java.util.Optional;


public interface UserService {

    User createUser(UserDto userDto);

    void deleteUser(long id);

    User updateUser(long id, UserDto user);

    Optional<User> findUserById(long id);
}
