package com.example.products.dto.requestDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@Setter
@ToString
public class ShippingAddressDto {

    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String homeAddress;


}
