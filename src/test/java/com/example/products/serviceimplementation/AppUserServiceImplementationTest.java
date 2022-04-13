package com.example.products.serviceimplementation;

import com.example.products.dto.requestDto.AppUserDto;
import com.example.products.entity.AppUser;
import com.example.products.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AppUserServiceImplementationTest {



    @Autowired
    AppUserServiceImplementation appUserServiceImplementation;

    @Test
    @DisplayName("Test that this  method creates user with role USER")
    void createUser() {
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setUsername("Ayodeji");
        appUserDto.setEmail("ayo@gmail.com");
        appUserDto.setPassword("pass");
        appUserDto.setFirstName("ayomide");
        appUserDto.setLastName("lala");

        AppUser user = appUserServiceImplementation.createUser(appUserDto);

        assertEquals(user.getRole(), Role.USER);


    }

    @Test
    @DisplayName("Test that this  method creates user with role ADMIN")
    void createAdmin() {

        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setUsername("Ayodej1i");
        appUserDto.setEmail("ayo@gmaiil.com");
        appUserDto.setPassword("pass2");
        appUserDto.setFirstName("ayomide");
        appUserDto.setLastName("lala");


        AppUser user = appUserServiceImplementation.createAdmin(appUserDto);
        assertEquals(user.getRole(), Role.ADMIN);

    }

}