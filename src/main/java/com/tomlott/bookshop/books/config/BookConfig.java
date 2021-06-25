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

            Branch branch = new Branch("Kazan");
            Branch secondBranch = new Branch("Moscow");

            Book a = new Book("Harry Potter", "J.K.Rolling", "abc", 2000, " Some description ", 10, branch.getName(), branch);
            Book b = new Book("Lord of the Rings", "J.R.R.Tolkien", "HHH", 1950, "  New description", 5, branch.getName(), branch);
            Book c = new Book("War and Peace", "Leo Tolstoy", "aaa", 1865, " About peace and war", 3, secondBranch.getName(), secondBranch);
            repository.saveAll(List.of(a, b, c));

            branchService.addBranch(branch);
            branchService.addBooksToBranch(a, 10, branch.getId());
//
//
            branchService.addBranch(secondBranch);
            branchService.addBooksToBranch(b, 5, branch.getId());
            branchService.addBooksToBranch(c, c.getAmount(), secondBranch.getId());
        };
    }
}
