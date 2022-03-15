package com.example.products.entity;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.products.entity.Authorities.*;


public enum Role {

    ADMIN(Sets.newHashSet(READ_USER, WRITE_USER, READ_COMPANY, WRITE_COMPANY, READ_PRODUCT, WRITE_PRODUCT)),
    USER(Sets.newHashSet(READ_USER));

    private final Set<Authorities> authoritiesSet;

    Role(Set<Authorities> authoritiesSet) {
        this.authoritiesSet = authoritiesSet;
    }

    public Set<Authorities> getAuthoritiesSet() {
        return authoritiesSet;
    }

    public Set<? extends GrantedAuthority> getGrantedAuthorities() {

        Set<GrantedAuthority> authorities = getAuthoritiesSet().stream().
                map(authorities1 -> new SimpleGrantedAuthority(authorities1.getAuthorities())).collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
