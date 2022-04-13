package com.example.products.dto.requestDto;

import lombok.Data;

@Data
public class ShippingAddressDto {
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String homeAddress;
}
