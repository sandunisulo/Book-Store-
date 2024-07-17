package com.backend.demo.service;

import com.backend.demo.dto.BookDto;
import com.backend.demo.entity.Book;
import com.backend.demo.entity.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book findBook(String bookName);

    BookDto findBookGetDto(String bookName);
    void saveBook(BookDto bookDto, Category category);

    List<BookDto> findAllBooks();

    List<BookDto> getBooksById(Long id);
    void deleteBook(Book book);
    void editBook(BookDto bookDto);

    Book getBookById(Long id);
}
