package com.backend.demo.service;

import com.backend.demo.dto.BookDto;
import com.backend.demo.entity.Book;

import java.util.List;

public interface BookService {
    Book findBook(String bookName);
    void saveBook(BookDto bookDto);

    List<BookDto> findAllBooks();
}
