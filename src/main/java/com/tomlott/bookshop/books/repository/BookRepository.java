package com.tomlott.bookshop.books.repository;

import com.tomlott.bookshop.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.name=?1")
    Optional<Book> findBookByName(String name);
}
