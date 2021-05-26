package com.tomlott.bookshop.books.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

    public Book(String name, String author, String publisher, int year, String plot) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.plot = plot;
    }
}
