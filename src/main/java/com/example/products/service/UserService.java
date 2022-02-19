package com.example.products.service;

import com.example.products.data.User;
import com.example.products.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {

    User createUser(User user);

    Optional<User> findUserById(int id);
}
