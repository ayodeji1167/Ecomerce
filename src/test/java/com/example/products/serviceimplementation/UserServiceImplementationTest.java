package com.example.products.serviceimplementation;

import com.example.products.dto.requestDto.UserDto;
import com.example.products.entity.AppUser;
import com.example.products.entity.Role;
import com.example.products.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplementationTest {



    @Autowired
    UserServiceImplementation userServiceImplementation;

    @Test
    @DisplayName("Test that this  method creates user with role USER")
    void createUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("Ayodeji");
        userDto.setEmail("ayo@gmail.com");
        userDto.setPassword("pass");

        AppUser user = userServiceImplementation.createUser(userDto);

        assertEquals(user.getRole(), Role.USER);


    }

    @Test
    @DisplayName("Test that this  method creates user with role ADMIN")
    void createAdmin() {

        UserDto userDto = new UserDto();
        userDto.setUsername("Ayodej1i");
        userDto.setEmail("ayo@gmaiil.com");
        userDto.setPassword("pass2");

        AppUser user = userServiceImplementation.createAdmin(userDto);
        assertEquals(user.getRole(), Role.ADMIN);

    }

}