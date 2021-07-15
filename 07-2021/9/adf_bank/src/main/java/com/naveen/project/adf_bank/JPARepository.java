package com.naveen.project.adf_bank;

import com.naveen.project.adf_bank.Models.BankAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepository extends JpaRepository<BankAccount,Integer> {
    
}
