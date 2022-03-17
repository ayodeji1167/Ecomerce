package com.example.products.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryResponseDto {

    private Long id;
    private String name;
    private String description;


}
