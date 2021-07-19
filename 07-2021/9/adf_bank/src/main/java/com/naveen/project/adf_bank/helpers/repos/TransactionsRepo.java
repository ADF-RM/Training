package com.naveen.project.adf_bank.helpers.repos;

import com.naveen.project.adf_bank.models.Transactions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepo extends JpaRepository<Transactions,Integer> {
    
}
