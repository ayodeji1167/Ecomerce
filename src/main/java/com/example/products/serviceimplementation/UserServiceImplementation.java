package com.example.products.serviceimplementation;

import com.example.products.dto.UserDto;
import com.example.products.entity.Role;
import com.example.products.entity.User;
import com.example.products.exception.UserNotFoundException;
import com.example.products.repository.UserRepo;
import com.example.products.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    //CREATE A USER
    @Override
    public User createUser(UserDto userDto) {
        User user = createNeutralUser(userDto);
        user.setRole(Role.USER);
        return userRepo.save(user);
    }

    //CREATE AN ADMIN
    public User createAdmin(UserDto userDto) {
        User admin = createNeutralUser(userDto);
        admin.setRole(Role.ADMIN);

        return userRepo.save(admin);
    }

    //METHOD FOR CREATING A NEUTRAL USER
    private User createNeutralUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setEnabled(true);
        return user;
    }

    //DELETE USER
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    //UPDATE USER
    public User updateUser(long id, UserDto userDto) {
        User user1 = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found"));
        user1.setId(id);
        user1.setUsername(userDto.getUsername());
        user1.setFirstName(userDto.getFirstName());
        user1.setLastName(userDto.getLastName());
        user1.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user1.setEnabled(true);
        user1.setEmail(userDto.getEmail());
        user1.setRole(Role.USER);

        return userRepo.save(user1);
    }


    //GET USER BY ID
    public Optional<User> findUserById(long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("This user is not found");
        }
        return user;
    }
}
