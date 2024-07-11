package com.backend.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String name;
    private String author;
    private String price;
    private String category;
    private String refernceNumber;
}
