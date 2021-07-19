package com.naveen.project.adf_bank.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.naveen.project.adf_bank.AdfBankApplication;
import com.naveen.project.adf_bank.helpers.CreateRequest;
import com.naveen.project.adf_bank.helpers.DateRequest;
import com.naveen.project.adf_bank.helpers.repos.BankAccountRepo;
import com.naveen.project.adf_bank.helpers.repos.TransactionsRepo;
import com.naveen.project.adf_bank.models.BankAccount;
import com.naveen.project.adf_bank.models.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
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

    Logger logger = AdfBankApplication.logger;

    @GetMapping(path = "/createAccount", produces = { "application/json" })
    public List<BankAccount> createAccount() {
        logger.info("Fetching BankAccount Details...");
        return bRepo.findAll();
    }

    @PostMapping(path = "/createAccount", consumes = { "application/json" })
    public List<BankAccount> createAccount(@RequestBody CreateRequest request, HttpServletResponse response)
            throws IOException {
        BankAccount bAccount = new BankAccount();
        Period period = Period.between(request.getDateOfBirth(), LocalDate.now());
        if (Math.abs(period.getYears()) >= 100) {
            logger.log(Level.WARNING, "Age should be less than 100 years");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Age greater than 100 is invalid");
            return null;
        }
        if ("current".equals(request.getAccountType().toLowerCase(Locale.US))) {
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
        logger.info("Account Creation Successful..");
        logger.info("Returning the accounts created");
        return bRepo.findAll();
    }

    @GetMapping(path = "/createTransactions/{accountNo}", produces = { "application/json" })
    public List<Transactions> createTransactions(@PathVariable Long accountNo) {
        BankAccount bAccount = bRepo.getById(accountNo);
        String message = "Fetching transactions of " + accountNo.toString();
        logger.info(message);
        return bAccount.getTransactions();
    }

    @ResponseBody
    @PostMapping(path = "/createTransactions/{accountNo}", consumes = { "application/json" })
    public List<Transactions> createTransactions(@RequestBody Transactions transactions, @PathVariable Long accountNo,
            HttpServletResponse response) throws IOException {

        if (!bRepo.existsById(accountNo)) {
            logger.info("Account Number doesn't exists..");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Account No. doesn't exist");
            return null;
        }
        BankAccount bAccount = bRepo.getById(accountNo);

        System.out.println("\n\nAccount Id : " + bRepo.getById(accountNo) + "\n\n");
        transactions.setOldBalance(bAccount.getBalance());

        System.out.println("\nA/c No." + bAccount.getAccountNo());

        switch (transactions.getTransactionType().toLowerCase(Locale.US)) {
            case "withdrawal" : {
                Double balance = bAccount.getBalance()
                        - (transactions.getTransactionAmount() + bAccount.getTransactionFee());
                if (balance > 0) {
                    transactions.setNewBalance(balance);
                    bAccount.setBalance(balance);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Insufficient balance..");
                    return null;
                }
                break;
            }
            case "deposit" : {
                Double balance = bAccount.getBalance() + transactions.getTransactionAmount()
                        - bAccount.getTransactionFee();
                transactions.setNewBalance(balance);
                bAccount.setBalance(balance);
                break;
            }
            default : {
                logger.info("Transaction Type invalid..");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Transaction Type");
                return null;
            }
        }

        transactions.setTransactionStatus("SUCCESS");
        transactions.setBankAccount(bAccount);
        tRepo.save(transactions);

        bAccount = bRepo.getById(accountNo);
        logger.info("Transaction Successful..");
        String message = "Returning Transactions done by " + accountNo.toString();
        logger.info(message);
        return bAccount.getTransactions();
    }

    @PostMapping(path = "/accountStatement/{accountNo}", produces = { "application/json" })
    public Map<String, Object> accountStatement(@PathVariable Long accountNo, @RequestBody DateRequest dRequest) {

        BankAccount bAccount = bRepo.getById(accountNo);

        System.out.println("\n\nDate Filter : \n\n");

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("account_number", accountNo);
        map.put("account_holder_name", bAccount.getHolderName());
        map.put("dob", bAccount.getDateOfBirth());
        map.put("account_type", bAccount.getAccountType());
        map.put("from_date", dRequest.getFromDate());
        map.put("to_date", dRequest.getToDate());

        List<Transactions> transactionsList = bRepo.getById(accountNo).getTransactions().stream()
                .filter(a -> Period.between((LocalDate) map.get("to_date"), a.getTransactionDate()).getDays() <= 0
                        && Period.between((LocalDate) map.get("from_date"), a.getTransactionDate()).getDays() >= 0)
                .collect(Collectors.toList());

        map.put("transactions", transactionsList);
        String message = "Fetching account statement of " + accountNo.toString();
        logger.info(message);
        return map;
    }

}
