package com.tomlott.bookshop.account.repository;

import com.tomlott.bookshop.account.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
        
}
