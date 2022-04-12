package com.example.products.controller;

import com.example.products.dto.requestDto.ShippingAddressDto;
import com.example.products.dto.requestDto.UserDto;
import com.example.products.entity.AppUser;
import com.example.products.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //CREATE A NORMAL USER
    @PostMapping("/add")
    public ResponseEntity<?> addNewUser(@RequestBody UserDto userDto) {
        AppUser appUser = userService.createUser(userDto);
        return new ResponseEntity<>(appUser, HttpStatus.OK);

    }

    //SET SHIPPING ADDRESS
    @PostMapping("/address/{userId}")
    public ResponseEntity<AppUser> setShippingAddress(@RequestBody ShippingAddressDto shippingAddressDto, @PathVariable long userId) {

        AppUser user = userService.setShippingAddress(shippingAddressDto, userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //CREATE AN ADMIN
    @PostMapping("/add/admin")
    public ResponseEntity<?> createAdmin(@RequestBody UserDto userDto) {
        AppUser appUser = userService.createAdmin(userDto);
        return new ResponseEntity<>(appUser, HttpStatus.OK);

    }


    //GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable int id) {
        AppUser user = userService.findUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //UPDATE A USER
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@RequestBody UserDto userDto, @PathVariable long id) {
        AppUser appUser = userService.updateUser(id, userDto);
        return new ResponseEntity<>(appUser, HttpStatus.OK);

    }


    //DELETE A USER
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("AppUser with id " + id + " deleted successfully!", HttpStatus.OK);

    }


}
