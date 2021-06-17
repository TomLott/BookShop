package com.tomlott.bookshop.books.config;

import com.tomlott.bookshop.books.model.Book;
import com.tomlott.bookshop.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(BookRepository repository) {
        return args -> {
            Book a = new Book("Harry Potter", "J.K.Rolling", "abc", 2000, " Some description ");
            Book b = new Book("Lord of the Rings", "J.R.R.Tolkien", "HHH", 1950, "  New description");
            repository.saveAll(List.of(a, b));

        };
    }
}
