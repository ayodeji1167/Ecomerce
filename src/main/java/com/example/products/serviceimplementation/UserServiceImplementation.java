package com.example.products.serviceimplementation;

import com.example.products.data.User;
import com.example.products.exception.UserNotFoundException;
import com.example.products.repository.UserRepo;
import com.example.products.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> findUserById(int id) {
       Optional<User> user = userRepo.findById(id);
       if (user.isEmpty()){
           throw new UserNotFoundException("This user is not found");
       }

       return user;
    }
}
