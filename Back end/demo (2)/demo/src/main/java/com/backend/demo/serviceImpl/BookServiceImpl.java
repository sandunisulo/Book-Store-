package com.backend.demo.serviceImpl;

import com.backend.demo.dto.BookDto;
import com.backend.demo.entity.Book;
import com.backend.demo.entity.Category;
import com.backend.demo.repository.BookRepository;
import com.backend.demo.repository.CategoryRepository;
import com.backend.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Book findBook(String bookName){
        return bookRepository.findByName(bookName);
    }

    @Override
    public BookDto findBookGetDto(String bookName){

        Book book =  bookRepository.findByName(bookName);
        BookDto bookDto = mapToBookDto(book);
        return bookDto;
    }


    @Override
    public void saveBook(BookDto bookDto,Category category){

        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setRefernceNumber(bookDto.getRefernceNumber());
        book.setPrice(bookDto.getPrice());
        System.out.println(bookDto.getCategory());
        book.setCategory(category);

        System.out.println("save book out");
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
        bookDto.setId(book.getId());
        bookDto.setCategory(book.getCategory().getName());
        bookDto.setPrice(book.getPrice());
        bookDto.setRefernceNumber(book.getRefernceNumber());

        return bookDto;
    }

    //get book by category id
    @Override
    public List<BookDto> getBooksById(Long id){
        List<Book> books  = bookRepository.findByCategoryId(id);

        List<BookDto> bookDtos = books.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
        System.out.println("Exiting findAllBooks method");
        return bookDtos;
    }

    @Override
    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    @Override
    public void editBook(BookDto bookDto){
        Book book = bookRepository.findByName(bookDto.getName());
        //Long a = 2L;
        //bookRepository.editBookDetails(a,bookDto.getName(),bookDto.getAuthor(),bookDto.getPrice());

    }

    @Override
    public Book getBookById(Long id){
        Optional<Book> book =  bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            Book book1 = new Book();
            return book1;
        }

    }
}
