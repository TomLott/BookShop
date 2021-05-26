package com.tomlott.bookshop.books.controller;


import com.tomlott.bookshop.books.model.Book;
import com.tomlott.bookshop.books.repository.BookRepository;
import com.tomlott.bookshop.books.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("admin")
public class AdminController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @GetMapping()
    public String index(){
        return "admin/index";
    }

    @GetMapping("/catalog/{id}")
    public String getBookPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("book", bookRepository.findById(id).orElseThrow(()-> new IllegalStateException("Book not found")));
        return "admin/bookPage";
    }

    @GetMapping("/catalog")
    public String getBooks(Model model){
        model.addAttribute("books", bookService.getBooks());
        return "admin/catalog";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id){
        bookRepository.deleteById(id);
        return "admin/index";
    }

    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, Model model){
        Book oldBook = bookRepository.findById(id).orElseThrow(()->new IllegalStateException("Book not found"));
        model.addAttribute("book", oldBook);
        log.debug("BEFORE UPDATE");
        return "admin/updateBook";
    }

    @PostMapping("/update/{id}")
    public String updateBookPatch(@ModelAttribute @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.debug("UPDATE ERROR");
            return "admin/updateBook";
        }
        bookService.updateBook(book.getId(), book);
        return "";
    }
}
