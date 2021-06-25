package com.tomlott.bookshop.books.service;

import com.tomlott.bookshop.books.model.Book;
import com.tomlott.bookshop.books.repository.BookRepository;
import com.tomlott.bookshop.branch.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BranchService branchService;

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

        book.setBranch(branchService.findByName(book.getBranchName()));
        bookRepository.save(book);
    }



    public void deleteBook(Long id){
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
        else
            throw new IllegalStateException("there is no such book");
    }

    @Transactional
    public void updateBook(Long id, Book book){
        Book oldBook;

        if ((oldBook = bookRepository.findById(id).orElse(null)) == null)
            throw new IllegalStateException("There is no such book to update");
        if (oldBook.getName().compareTo(book.getName()) == 0
                && oldBook.getAuthor().compareTo(book.getAuthor()) == 0
                && oldBook.getBranch().getName().equals(book.getBranch().getName())
                && oldBook.getAmount() == book.getAmount()
                && branchService.getBookAmount(book.getId(), book.getBranch().getId()) == branchService.getBookAmount(oldBook.getId(), oldBook.getBranch().getId()))
            throw new IllegalStateException("This book exists already");
        oldBook.setName(book.getName());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setPublisher(book.getPublisher());
        oldBook.setYear(book.getYear());
        oldBook.setPlot(book.getPlot());
        oldBook.setBranchName(book.getBranchName());
        oldBook.setAmount(book.getAmount());
        oldBook.setBranch(book.getBranch());
        branchService.changeAmount(oldBook.getId(), oldBook.getAmount(), oldBook.getBranch().getId());
    }

    public Book findById(Long id){
        return bookRepository.findById(id).orElse(null);
    }
}
