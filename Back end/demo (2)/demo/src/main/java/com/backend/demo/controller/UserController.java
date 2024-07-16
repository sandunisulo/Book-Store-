package com.backend.demo.controller;


import com.backend.demo.dto.BookDto;
import com.backend.demo.dto.UserDto;
import com.backend.demo.dto.UserLoginDto;
import com.backend.demo.entity.User;
import com.backend.demo.service.BookService;
import com.backend.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;


    @GetMapping("/login")
    public String login(Model model){
        UserLoginDto user = new UserLoginDto();
        model.addAttribute("user",user);
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUser(userDto.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }else{
            userService.saveUser(userDto);
            return "redirect:/register?success";

        }
    }

    @PostMapping("/load")
    public String userLogin(@Valid @ModelAttribute("user") UserLoginDto userLoginDto,
                     BindingResult result,
                     Model model){
        User existingUser = userService.findUser(userLoginDto.getEmail());
        System.out.println(existingUser.getUserName());
        System.out.println(existingUser.getEmail());
        System.out.println(existingUser.getPassword());
        if(existingUser == null && existingUser.getEmail() == null && existingUser.getEmail().isEmpty()){
            return "redirect:/login?noUser";
        } else {

            if(existingUser.getPassword().equals(userLoginDto.getPassword())){
                return "redirect:/index?success";
            }else{
                return "redirect:/login?wrongPassword";

            }
        }
    }
}
