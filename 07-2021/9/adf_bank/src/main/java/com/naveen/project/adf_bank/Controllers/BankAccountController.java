package com.naveen.project.adf_bank.Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.naveen.project.adf_bank.Models.BankAccount;
import com.naveen.project.adf_bank.Models.Transactions;
import com.naveen.project.adf_bank.Utils.CreateRequest;
import com.naveen.project.adf_bank.Utils.Repos.BankAccountRepo;
import com.naveen.project.adf_bank.Utils.Repos.TransactionsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController {
    @Autowired
    BankAccountRepo bRepo;
    @Autowired
    TransactionsRepo tRepo;

    @GetMapping(path = "/createAccount", produces = { "application/json" })
    public List<BankAccount> createAccount() {
        return bRepo.findAll();
    }

    @PostMapping(path = "/createAccount", consumes = { "application/json" })
    public List<BankAccount> createAccount(@RequestBody CreateRequest request) {
        BankAccount bAccount = new BankAccount();
        if ((request.getAccountType().toLowerCase()).equals("current")) {
            bAccount.setTransactionFee(5.0);
        }
        bAccount.setDateOfBirth(request.getDateOfBirth());
        bAccount.setHolderName(request.getHolderName());
        bAccount.setAccountType(request.getAccountType());
        bAccount.setBalance(request.getInitialDeposit());
        bRepo.save(bAccount);
        if (request.getInitialDeposit() > 0.0) {
            Transactions transactions = new Transactions();
            transactions.setTransactionAmount(request.getInitialDeposit());
            transactions.setBankAccount(bAccount);
            transactions.setNewBalance(request.getInitialDeposit() - bAccount.getTransactionFee());
            transactions.setTransactionStatus("SUCCESS");
            tRepo.save(transactions);

            bAccount.setBalance(transactions.getNewBalance());
            bAccount.setTransactions(tRepo.findAll());
            bRepo.save(bAccount);
        }
        return bRepo.findAll();
    }

    @GetMapping(path = "/createTransactions/{accountNo}", produces = { "application/json" })
    public List<Transactions> createTransactions(@PathVariable Long accountNo) {
        BankAccount bAccount = bRepo.getById(accountNo);
        return bAccount.getTransactions();
    }

    @ResponseBody
    @PostMapping(path = "/createTransactions/{accountNo}", consumes = { "application/json" })
    public List<Transactions> createTransactions(@RequestBody Transactions transactions, @PathVariable Long accountNo,
            HttpServletResponse response) throws IOException {

        if (!bRepo.existsById(accountNo)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Account No. doesn't exist");
            return null;
        }
        BankAccount bAccount = bRepo.getById(accountNo);

        System.out.println("\n\nAccount Id : " + bRepo.getById(accountNo) + "\n\n");
        transactions.setOldBalance(bAccount.getBalance());

        System.out.println("\nA/c No." + bAccount.getAccountNo());

        switch (transactions.getTransactionType().toLowerCase()) {
            case "withdrawal" -> {
                Double balance = bAccount.getBalance()
                        - (transactions.getTransactionAmount() + bAccount.getTransactionFee());
                if (balance > 0) {
                    transactions.setNewBalance(balance);
                    bAccount.setBalance(balance);
                } else {
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                    response.sendError(HttpStatus.CONFLICT.value(), "Conflict_Occured...Check balance");
                    return null;
                }
            }
            case "deposit" -> {
                Double balance = bAccount.getBalance() + transactions.getTransactionAmount()
                        - bAccount.getTransactionFee();
                transactions.setNewBalance(balance);
                bAccount.setBalance(balance);
            }
        }

        transactions.setTransactionStatus("SUCCESS");
        transactions.setBankAccount(bAccount);
        tRepo.save(transactions);

        bAccount = bRepo.getById(accountNo);
        return bAccount.getTransactions();
    }
}