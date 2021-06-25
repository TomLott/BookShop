package com.tomlott.bookshop.branch.service;

import com.tomlott.bookshop.books.model.Book;
import com.tomlott.bookshop.branch.model.Branch;
import com.tomlott.bookshop.branch.repository.BranchRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@AllArgsConstructor
public class BranchService{

    private final BranchRepository branchRepository;

    public Branch findById(Long branchId) {
        return branchRepository.findById(branchId).orElse(null);
    }

    public Branch findByName(String name) {
        return branchRepository.findByName(name).orElse(null);
    }

    public List<Branch> getBranches(){
        return branchRepository.findAll();
    }

    public void removeBranch(Long branchId){
        Branch branch =  findById(branchId);
        if (branch != null)
            branchRepository.delete(branch);
    }

    public void addBranch(Branch branch){
        branchRepository.save(branch);
    }

    public void updateBranch(Branch branch, Long branchId){
        Branch oldBranch = findById(branchId);
        oldBranch.setName(branch.getName());
        branchRepository.save(oldBranch);
    }

    public void addBooksToBranch(Book book, int amount, Long branchId){
        branchRepository.addBook(book.getId(), amount, branchId);
    }

    public void changeAmount(Long bookId, int amount, Long branchId){
        branchRepository.changeAmount(bookId, amount, branchId);
    }

    public int getBookAmount(Long bookId, Long branchId){
        return branchRepository.getBookAmount(bookId, branchId);
    }


}
