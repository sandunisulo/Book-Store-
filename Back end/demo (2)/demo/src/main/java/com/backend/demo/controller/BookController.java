package com.backend.demo.controller;

import com.backend.demo.dto.BookDto;
import com.backend.demo.entity.Book;
import com.backend.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/addBook")
    public String showAddBookForm(Model model){

        BookDto bookDto = new BookDto();
        model.addAttribute("book", bookDto);
        return "addBook";
    }

    //adding book managing
    @PostMapping("/addBook/load")
    public String addBook(@Valid @ModelAttribute("book") BookDto bookDto,
                          BindingResult result,
                          Model model){
        Book existingBook = bookService.findBook(bookDto.getName());
        if(existingBook != null && existingBook.getName() != null && !existingBook.getName().isEmpty()){
            return "redirect:/addBook?fail";
        }else{
            bookService.saveBook(bookDto);
            return "redirect:/addBook?success";

        }

    }

    @GetMapping("/books")
    public String showBook(Model model){
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
        return "books";
    }
}
