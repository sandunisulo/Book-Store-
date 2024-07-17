package com.backend.demo.repository;

import com.backend.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Book findByName(String name);
    List<Book> findByCategoryId(Long categoryId);
    Optional<Book> findById(Long id);

}
