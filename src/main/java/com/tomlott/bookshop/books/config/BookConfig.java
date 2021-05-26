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
            Book a = new Book("Harry Potter", "ssasdf", "adsfsadsa", 2000, " dsaf ");
            Book b = new Book("Harry Potter2", "ssasf", "sdasffsa", 2000, "  assadfdf");
            repository.saveAll(List.of(a, b));

        };
    }
}
