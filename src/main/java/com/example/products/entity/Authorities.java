package com.example.products.entity;


public enum Authorities {

    READ_USER("read:user"),
    WRITE_USER("write:user"),
    READ_COMPANY("read:company"),
    WRITE_COMPANY("write:company"),
    READ_PRODUCT("read:product"),
    WRITE_PRODUCT("write:product");


    private final String authorities;


    Authorities(String authorities) {
        this.authorities = authorities;
    }

    public String getAuthorities() {
        return authorities;
    }
}
