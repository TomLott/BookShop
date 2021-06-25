package com.tomlott.bookshop.branch.repository;

import com.tomlott.bookshop.branch.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Transactional
    @Modifying
    @Query(value  = "INSERT INTO branch_books(book_id, amount, branch_list_key) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void addBook(Long bookId, int amount, Long branchId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE branch_books SET amount = ?2 WHERE book_id = ?1", nativeQuery = true)
    void changeAmount(Long bookId, int amount, Long branchId);

    Optional<Branch> findByName(String name);

    @Transactional
    @Query(value = "SELECT amount FROM branch_books WHERE book_id=?1 AND branch_list_key=?2", nativeQuery = true)
    int getBookAmount(Long bookId, Long branchId);
}
