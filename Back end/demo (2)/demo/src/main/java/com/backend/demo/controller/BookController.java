package com.backend.demo.controller;

import com.backend.demo.dto.BookDto;
import com.backend.demo.dto.EditBookDto;
import com.backend.demo.entity.Book;
import com.backend.demo.entity.Category;
import com.backend.demo.service.BookService;
import com.backend.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(@RequestParam(required = false) Long categoryId, Model model){
        System.out.println("Entering showBook method");
        List<BookDto> books;
        if (categoryId != null) {
            books = bookService.getBooksById(categoryId);
        } else {
            books = bookService.findAllBooks();
        }
        if (books == null) {
            System.out.println("Books list is null.");
        } else if (books.isEmpty()) {
            System.out.println("Books list is empty.");
        } else {
            System.out.println("Books list size: " + books.size());
            System.out.println("Author of the first book: " + books.get(0).getAuthor());
        }
        model.addAttribute("books",books);
        model.addAttribute("categories", categoryService.getAllCategory());
        System.out.println("out going showBook method");
        return "index";
    }

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
        }
        Category existingCategory = categoryService.getCategoryByName(bookDto.getCategory());
        if(existingCategory == null){
            Category category = new Category();
            category.setName(bookDto.getCategory());
            category.setBookCatId(categoryService.generateUniqueReferenceNumber());
            System.out.println("save category in");
            categoryService.save(category);
            System.out.println("save book in");
            bookService.saveBook(bookDto,categoryService.getCategoryByName(bookDto.getCategory()));

            return "redirect:/addBook?success";
        } else{

            bookService.saveBook(bookDto,existingCategory);
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

    @GetMapping("/editBooks")
    public String editBookLoad(Model model){
        List<BookDto> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "editBooks";
    }

    @GetMapping("/editBooks/edit/{name}")
    public ModelAndView editBook(@PathVariable("name") String name,
                                 Model model
                            ){
        ModelAndView modelAndView = new ModelAndView("edit");
        BookDto bookDto = bookService.findBookGetDto(name);
        modelAndView.addObject("book" , bookDto);

        return modelAndView;
    }

    @PostMapping("/editBooks/edit/check")
    public String editCheck(@Valid @ModelAttribute("newBook") BookDto bookDto,
                            BindingResult result,
                            Model model){
        Book book = bookService.getBookById(bookDto.getId());
        bookService.deleteBook(book);
        Book existingBook = bookService.findBook(bookDto.getName());

        Category existingCategory = categoryService.getCategoryByName(bookDto.getCategory());
        if(existingCategory == null){
            Category category = new Category();
            category.setName(bookDto.getCategory());
            category.setBookCatId(categoryService.generateUniqueReferenceNumber());
            System.out.println("save category in");
            categoryService.save(category);
            System.out.println("save book in");
            bookService.saveBook(bookDto,categoryService.getCategoryByName(bookDto.getCategory()));

        } else{
            bookService.saveBook(bookDto,existingCategory);

        }
        return "redirect:/editBooks";
    }

    @GetMapping("/editBooks/delete/{name}")
    public String deleteBook(@PathVariable("name") String name){
        Book book = bookService.findBook(name);
        System.out.println(name);
        if(book==null){
            System.out.println(book.getName());
            return "redirect:/editBooks";
        }else{
            bookService.deleteBook(book);
            System.out.println(book.getName());
            return "redirect:/editBooks";
        }
    }
}
