package com.tomlott.bookshop.account.service;

import com.tomlott.bookshop.account.repository.CartRepository;
import com.tomlott.bookshop.books.model.Book;
import com.tomlott.bookshop.books.service.BookService;
import com.tomlott.bookshop.user.appuser.AppUser;
import com.tomlott.bookshop.user.appuser.AppUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final BookService bookService;
    private final AppUserService appUserService;
    private AppUser user = null;


    private void init() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        user = appUserService.getUsers().stream().filter(a->a.getEmail().compareTo(username) == 0).findFirst().orElse(null);
    }


    public CartService(CartRepository cartRepository, BookService bookService, AppUserService appUserService) {
        this.cartRepository = cartRepository;
        this.bookService = bookService;
        this.appUserService = appUserService;
    }

    public List<Book> getBooks(){
        init();
        System.out.println(user.getEmail());
        System.out.println(user.getCart().getId());
        List<Book> books = bookService.getBooks().stream().filter(a->a.getCart() != null && a.getCart().getId().equals(user.getCart().getId())).collect(Collectors.toList());
        System.out.println("!!!!!!!!!!!");
        System.out.println(books);
        System.out.println("!!!!!!!!!!!");
        return books;
    }

    public void addBook(Long bookId){
       // bookService.getBooks().stream().filter(a->a.getCart().equals(user.getCart())).findFirst().get().setCart(user.getCart());
//          bookService.getBooks().stream().filter(a->a.getId)
        Book book = bookService.findById(bookId);
        System.out.println("!!@!&$@!_%!@U$!@!!!");
        System.out.println(user.getCart());
        System.out.println(book.getName());
        book.setCart(user.getCart());
        System.out.println(book.getCart());
        bookService.getBookRepository().save(book);
    }
}