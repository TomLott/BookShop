package com.tomlott.bookshop.branch.model;

import com.tomlott.bookshop.books.model.Book;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Branch {

    @Id
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name="books_in_branch",
            joinColumns=@JoinColumn(name="branch_id"),
            inverseJoinColumns=@JoinColumn(name="book_id")
            )
    private List<Book> bookList;
}
