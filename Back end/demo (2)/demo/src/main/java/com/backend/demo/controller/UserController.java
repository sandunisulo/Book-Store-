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


    // handler method to handle home page request
    @GetMapping("/index")
    public String home(Model model){
        System.out.println("Entering showBook method");
        List<BookDto> books = bookService.findAllBooks();
        if (books == null) {
            System.out.println("Books list is null.");
        } else if (books.isEmpty()) {
            System.out.println("Books list is empty.");
        } else {
            System.out.println("Books list size: " + books.size());
            System.out.println("Author of the first book: " + books.get(0).getAuthor());
        }
        model.addAttribute("books",books);
        System.out.println("out going showBook method");
        return "index";
    }


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
