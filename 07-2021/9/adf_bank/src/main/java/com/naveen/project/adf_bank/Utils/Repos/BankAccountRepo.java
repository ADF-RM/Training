package com.naveen.project.adf_bank.Utils.Repos;

import com.naveen.project.adf_bank.Models.BankAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="accounts", collectionResourceRel = "accounts")
public interface BankAccountRepo extends JpaRepository<BankAccount,Long> {
    
}
