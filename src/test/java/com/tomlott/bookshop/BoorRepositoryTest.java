package com.tomlott.bookshop;


import com.tomlott.bookshop.books.model.Book;
import com.tomlott.bookshop.books.repository.BookRepository;
import com.tomlott.bookshop.branch.model.Branch;
import com.tomlott.bookshop.branch.service.BranchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoorRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BranchService branchService;

    @ParameterizedTest
    @ValueSource(strings = {"Harry Potter"})
    void itShouldCheckIfFindBookByName(String name){
        Assertions.assertNotNull(bookRepository.findBookByName(name).orElse(null));
    }

}
