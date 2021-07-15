package com.naveen.project.adf_bank.Utils.Repos;

import com.naveen.project.adf_bank.Models.Transactions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepo extends JpaRepository<Transactions,Integer> {
    
}
