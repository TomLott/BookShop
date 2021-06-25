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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

//    @ManyToMany
//    @JoinTable(
//            name="books_in_branch",
//            joinColumns=@JoinColumn(name="branch_id"),
//            inverseJoinColumns=@JoinColumn(name="book_id")
//            )
//
    @OneToMany(mappedBy = "branch")
    private List<Book> bookList;



    public Branch(String name){
        this.name = name;
    }


    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
