package com.example.products.controller;

import com.example.products.data.User;
import com.example.products.serviceimplementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @PostMapping("/user")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        userServiceImplementation.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable int id){
        Optional<User> user=   userServiceImplementation.findUserById(id);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }


}
