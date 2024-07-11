package com.backend.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotEmpty(message="User name can not be empty")
    private String userName;
    @NotEmpty(message = "Email can not be empty")
    @Email
    private String email;
    private String address;
    private String phoneNumber;
    @NotEmpty(message = "password can not be empty")
    private String password;
}
