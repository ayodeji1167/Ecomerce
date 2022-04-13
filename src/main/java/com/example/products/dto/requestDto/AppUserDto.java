package com.example.products.dto.requestDto;

import lombok.Data;

@Data
public class AppUserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}