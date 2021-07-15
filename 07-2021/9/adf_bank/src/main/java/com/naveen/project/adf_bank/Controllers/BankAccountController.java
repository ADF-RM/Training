package com.naveen.project.adf_bank.Controllers;

import java.time.LocalDate;
import java.util.List;

import com.naveen.project.adf_bank.JPARepository;
import com.naveen.project.adf_bank.Models.BankAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController {
    @Autowired
    JPARepository repo;

    @GetMapping(path = "/", produces = { "application/json" })
    public List<BankAccount> home() {
        return repo.findAll();
    }

    @PostMapping(path = "/", consumes = { "application/json" })
    public List<BankAccount> home(@RequestBody BankAccount bAccount) {
        if ((bAccount.getAccountType().toLowerCase()).equals("current")) {
            bAccount.setTransactionFee(5.0);
        }
        bAccount.setBalance(bAccount.getInitialDeposit());
        repo.save(bAccount);
        return repo.findAll();
    }
}
