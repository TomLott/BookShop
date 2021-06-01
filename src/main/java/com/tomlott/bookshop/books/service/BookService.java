package com.tomlott.bookshop.books.service;

import com.tomlott.bookshop.books.model.Book;
import com.tomlott.bookshop.books.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public void addNewBook(Book book){
        Optional<Book> opt = bookRepository.findBookByName(book.getName());

        if (opt.isPresent())
            throw new IllegalStateException("name is used");

        bookRepository.save(book);
    }



    public void deleteBook(Long id){
        if (bookRepository.existsById(id))
            bookRepository.deleteById(id);
        else
            throw new IllegalStateException("there is no such book");
    }

    @Transactional
    public void updateBook(Long id, Book book){
        Book oldBook;

        if ((oldBook = bookRepository.findById(id).orElse(null)) == null)
            throw new IllegalStateException("There is no such book to update");
        if (oldBook.getName().compareTo(book.getName()) == 0
                && oldBook.getAuthor().compareTo(book.getAuthor()) == 0)
            throw new IllegalStateException("This book exists already");
        oldBook.setName(book.getName());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setPublisher(book.getPublisher());
        oldBook.setYear(book.getYear());
        oldBook.setPlot(book.getPlot());
    }

    public Book findById(Long id){
        return bookRepository.findById(id).orElse(null);
    }
}
