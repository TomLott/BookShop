package com.tomlott.bookshop.branch.model;

import com.tomlott.bookshop.books.model.Book;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @UniqueElements
    private String name;

//    @ManyToMany
//    @JoinTable(
//            name="books_in_branch",
//            joinColumns=@JoinColumn(name="branch_id"),
//            inverseJoinColumns=@JoinColumn(name="book_id")
//            )
//    private List<Book> bookList;

    public Branch(String name){
        this.name = name;
    }
}
