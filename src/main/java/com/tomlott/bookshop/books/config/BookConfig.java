package com.tomlott.bookshop.books.config;

import com.tomlott.bookshop.books.model.Book;
import com.tomlott.bookshop.books.repository.BookRepository;
import com.tomlott.bookshop.branch.model.Branch;
import com.tomlott.bookshop.branch.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(BookRepository repository, BranchService branchService) {
        return args -> {

            Branch branch = new Branch("first");
            Branch secondBranch = new Branch("second");

            Book a = new Book("Harry Potter", "J.K.Rolling", "abc", 2000, " Some description ", 10, branch.getName());
            Book b = new Book("Lord of the Rings", "J.R.R.Tolkien", "HHH", 1950, "  New description", 5, branch.getName());
            repository.saveAll(List.of(a, b));

//            branchService.addBranch(branch);
//            branchService.addBooksToBranch(a, 10, branch.getId());
//
//
//            branchService.addBranch(secondBranch);
//            branchService.addBooksToBranch(b, 5, secondBranch.getId());
        };
    }
}
