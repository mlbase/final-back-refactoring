package com.portfolio.socialbooksotre.books.repository;

import com.portfolio.socialbooksotre.books.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BooksRepository extends JpaRepository<Books, Long> {

    Optional<Books> findByTitle(String title);

    Optional<Books> findByIsbn(String isbn);
}
