package com.tomlott.bookshop.account.controller;


import com.tomlott.bookshop.account.service.CartService;
import com.tomlott.bookshop.books.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Controller
@RequestMapping("/account")
public class AccountController {

    private final CartService cartService;

    @GetMapping()
    public String index(){
        return "account/index";
    }

    @GetMapping("/cart")
    public String shoppingCart(Model model){
        model.addAttribute("books", cartService.getBooks());
        return "account/cart";
    }

    @PostMapping("/add-cart/{id}")
    public String addBook(@PathVariable("id") Long bookId){
        cartService.addBook(bookId);
        return "account/index";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long bookId){
        cartService.deleteBook(bookId);
        return "account/index";
    }

    @GetMapping("/success")
    public String successful(){
        return "account/success";
    }

    @PostMapping("/buyAllBooks")
    public String buyBooks(){
        try {
            cartService.deleteAllBooks();
        } catch (RuntimeException e){
            return "account/cart";
        }
        return "redirect:/account/success";
    }
}
