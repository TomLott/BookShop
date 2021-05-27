package com.tomlott.bookshop.account.controller;


import com.tomlott.bookshop.books.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/account")
public class AccountController {

    private final BookService bookService;

    @GetMapping()
    public String index(){
        return "account/index";
    }

    @GetMapping("/cart")
    public String shoppingCart(Model model){
        return "account/cart";
    }
}