package com.tomlott.bookshop.books.model;


import com.tomlott.bookshop.account.model.Cart;
import com.tomlott.bookshop.branch.model.Branch;
import com.tomlott.bookshop.branch.service.BranchService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Book's title cannot be empty")
    @Size(min = 3, max = 100, message = "Book's title's length should be from 3 to 100")
    private String name;
    @NotEmpty(message = "Book's author name cannot be empty")
    @Size(min = 3, max = 100, message = "Book's author's name should be from 3 to 100")
    private String author;
    @NotEmpty(message = "Book's publisher cannot be empty")
    @Size(min = 3, max = 100, message = "Book's publisher's name should be from 3 to 100")
    private String publisher;
    @Min(value = 1000, message = "Book publishing year cannot be less than 1000")
    @Max(value = 2021, message = "Book publishing year cannot be more than 2021")
    private int    year;
    @NotEmpty(message = "Book's description cannot be empty")
    @Size(min = 1, max = 100, message = "Book's description's length cannot be more than 1000")
    private String plot;

    @ManyToOne
    private Cart cart;

//    @ManyToOne
//    @JoinColumn(name="branch_id", nullable = false)
//    private Branch branch;

//    @Transient
    private int amount;

//    @Transient
    private String branchName;

    @ElementCollection
    @CollectionTable(name = "branch_books",
            joinColumns = {@JoinColumn(name = "book_id",
            referencedColumnName = "id")})
    @MapKeyColumn(name="branch_name")
    @Column(name = "amount")
    private Map<Branch, Long> branchList = new HashMap();

    public Book(String name, String author, String publisher, int year, String plot, int amount, String branchName) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.plot = plot;
        this.amount = amount;
        this.branchName = branchName;
    }
}
