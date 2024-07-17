package com.backend.demo.dto;

import com.backend.demo.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    @NotEmpty(message="Name cannot be empty")
    private String name;
    @NotEmpty(message = "Author can not be empty")
    private String author;
    @NotEmpty(message = "Price can not be empty")
    private String price;
    @NotEmpty(message = "Category can not be empty")
    private String category;
    @NotEmpty(message = "Reference Number can not be empty")
    private String refernceNumber;
}
