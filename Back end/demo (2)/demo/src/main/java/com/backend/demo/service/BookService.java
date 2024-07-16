package com.backend.demo.service;

import com.backend.demo.dto.BookDto;
import com.backend.demo.entity.Book;
import com.backend.demo.entity.Category;

import java.util.List;

public interface BookService {
    Book findBook(String bookName);
    void saveBook(BookDto bookDto, Category category);

    List<BookDto> findAllBooks();

    List<BookDto> getBooksById(Long id);
    void deleteBook(Book book);
}
