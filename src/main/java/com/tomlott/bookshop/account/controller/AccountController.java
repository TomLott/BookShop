package com.tomlott.bookshop.account.controller;


import com.tomlott.bookshop.account.service.CartService;
import com.tomlott.bookshop.books.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/account")
public class AccountController {

    private final BookService bookService;
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
        System.out.println("!!!!!!!!");
        cartService.addBook(bookId);
        return "account/index";
    }
}
