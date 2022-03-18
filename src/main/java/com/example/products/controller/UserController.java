package com.example.products.controller;

import com.example.products.dto.requestDto.UserDto;
import com.example.products.entity.User;
import com.example.products.serviceimplementation.UserServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserServiceImplementation userServiceImplementation;

    public UserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }


    //CREATE A NORMAL USER
    @PostMapping("/add/user")
    public ResponseEntity<?> addNewUser(@RequestBody UserDto userDto) {
        User user = userServiceImplementation.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    //CREATE AN ADMIN
    @PostMapping("/add/admin")
    public ResponseEntity<?> createAdmin(@RequestBody UserDto userDto) {
        User user = userServiceImplementation.createAdmin(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    //GET USER BY ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable int id) {
        Optional<User> user = userServiceImplementation.findUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //UPDATE A USER
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto , @PathVariable long id){
        User user = userServiceImplementation.updateUser(id,userDto);
        return new ResponseEntity<>(user,HttpStatus.OK);

    }


    //DELETE A USER
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable long id) {
        userServiceImplementation.deleteUser(id);
        return new ResponseEntity<>("User with id " + id + " deleted successfully!", HttpStatus.OK);

    }


}
