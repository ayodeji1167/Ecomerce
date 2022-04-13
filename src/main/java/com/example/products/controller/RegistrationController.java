package com.example.products.controller;

import com.example.products.dto.requestDto.AppUserDto;
import com.example.products.entity.AppUser;
import com.example.products.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }



    //CREATE A NORMAL USER
    @PostMapping("/user")
    public ResponseEntity<?> addNewUser(@RequestBody AppUserDto appUserDto) {
        AppUser appUser = userService.createUser(appUserDto);
        return new ResponseEntity<>(appUser, HttpStatus.OK);

    }
    //CREATE AN ADMIN
    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(@RequestBody AppUserDto appUserDto) {
        AppUser appUser = userService.createAdmin(appUserDto);
        return new ResponseEntity<>(appUser, HttpStatus.OK);

    }

}
