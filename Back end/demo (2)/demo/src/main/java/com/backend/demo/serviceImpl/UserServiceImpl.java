package com.backend.demo.serviceImpl;

import com.backend.demo.dto.UserDto;
import com.backend.demo.dto.UserLoginDto;
import com.backend.demo.entity.User;
import com.backend.demo.repository.UserRepository;
import com.backend.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    /*@Autowired
    private PasswordEncoder passwordEncoder;
    public void UserService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }*/



    //find user by email
    @Override
    public User findUser(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(UserDto userDto){

            User user = new User();
            user.setUserName(userDto.getUserName());
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(userDto.getPassword());

            //encrypt the password
            //user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);

    }


}
