package com.backend.demo.serviceImpl;

import com.backend.demo.dto.BookDto;
import com.backend.demo.entity.Book;
import com.backend.demo.repository.BookRepository;
import com.backend.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findBook(String bookName){
        return bookRepository.findByName(bookName);
    }

    @Override
    public void saveBook(BookDto bookDto){
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setRefernceNumber(bookDto.getRefernceNumber());
        book.setCategory(bookDto.getCategory());
        book.setPrice(bookDto.getPrice());

        bookRepository.save(book);

    }

    @Override
    public List<BookDto> findAllBooks(){
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = books.stream()
                .map(book -> mapToBookDto(book))
                .collect(Collectors.toList());
        System.out.println("Exiting findAllBooks method");
        return bookDtos;
    }

    private BookDto mapToBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setCategory(book.getCategory());
        bookDto.setPrice(book.getPrice());
        bookDto.setRefernceNumber(book.getRefernceNumber());

        return bookDto;
    }
}
