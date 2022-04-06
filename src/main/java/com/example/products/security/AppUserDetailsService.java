package com.example.products.security;

import com.example.products.entity.AppUser;
import com.example.products.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;


    public AppUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepo.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return new AppUserDetails(appUser);
    }
}
