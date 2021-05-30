package com.tomlott.bookshop.account.model;


import com.tomlott.bookshop.books.model.Book;
import com.tomlott.bookshop.user.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Component
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<Book> books;

    @OneToOne(mappedBy = "cart")
    private AppUser appUser;

}
