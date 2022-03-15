package com.example.products.security;

import com.example.products.entity.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class AppUserDetails implements UserDetails {
    private final Set<? extends GrantedAuthority> authorities;
    private final String password;
    private final String username;
    private final boolean isEnabled;

    private User user;



    public AppUserDetails( User user1) {
        this.authorities = user1.getRole().getGrantedAuthorities();
        this.isEnabled = user1.isEnabled();
        this.password = user1.getPassword();
        this.username = user1.getUsername();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
