package com.tomlott.bookshop.books.controller;


import com.tomlott.bookshop.books.model.Book;
import com.tomlott.bookshop.books.repository.BookRepository;
import com.tomlott.bookshop.books.service.BookService;
import com.tomlott.bookshop.branch.model.Branch;
import com.tomlott.bookshop.branch.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final BranchService branchService;

    @GetMapping()
    public String index(){
        return "books/index";
    }

    @GetMapping("/catalog")
    public String showBranches(Model model){
        model.addAttribute("branches", branchService.getBranches());
        return "books/branches";
    }

    @GetMapping("/catalog/{branch}")
    public String showCatalog(Model model, @PathVariable("branch") String branchName){
        Branch branch = branchService.getBranches().stream().filter(a->a.getName().compareToIgnoreCase(branchName) == 0).findFirst().orElse(null);
        if (branch == null)
            throw new RuntimeException("There is no branch named \"" + branchName + "\"" );
        model.addAttribute("books", bookService.getBooks().stream().filter(a->a.getBranchName().equals(branchName)).collect(Collectors.toList()));
        return "books/catalog";
    }

    @GetMapping("{id}")
    public String getBook(Model model, @PathVariable("id") Long id){
        model.addAttribute("book", bookRepository.findById(id).orElseThrow(()->new IllegalStateException("Book not found")));
        return "books/bookPage";
    }

    @GetMapping("/add-book")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "books/addBook";
    }

    @PostMapping("/add-book")
    public String addBookPost(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors() || !branchService.getBranches().stream().map(Branch::getName).collect(Collectors.toSet()).contains(book.getBranchName()))
            return "books/addBook";
        bookService.addNewBook(book);
        return ("admin/index");
    }

}

