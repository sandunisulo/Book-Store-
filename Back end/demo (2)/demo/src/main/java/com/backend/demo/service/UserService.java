package com.backend.demo.service;

import com.backend.demo.dto.UserDto;
import com.backend.demo.dto.UserLoginDto;
import com.backend.demo.entity.User;

public interface UserService {

    void saveUser(UserDto userDto);

    User findUser(String email);


}
