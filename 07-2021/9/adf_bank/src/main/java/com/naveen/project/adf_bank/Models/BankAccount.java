package com.naveen.project.adf_bank.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class BankAccount {
    @Id
    @GenericGenerator(name = "acc_no", strategy = "com.naveen.project.AccountNumberGenerator")
    @GeneratedValue(generator = "acc_no")
    private Long accountNo;
    private String holderName;
    private LocalDate dateOfBirth;
    private String accountType;
    private Double initialDeposit = 0.0;
    private Double balance = 0.0;
    private Double transactionFee = 0.0;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "bankAccount")
    private List<Transactions> transactions = new ArrayList<>();

    public Long getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }
    public String getHolderName() {
        return holderName;
    }
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate localDate) {
        this.dateOfBirth = localDate;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Double getInitialDeposit() {
        return initialDeposit;
    }
    public void setInitialDeposit(Double initialDeposit) {
        this.initialDeposit = initialDeposit;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public Double getTransactionFee() {
        return transactionFee;
    }
    public void setTransactionFee(Double transactionFee) {
        this.transactionFee = transactionFee;
    }
    public List<Transactions> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "BankAccount [accountNo=" + accountNo + ", accountType=" + accountType + ", balance=" + balance
                + ", createdAt=" + createdAt + ", dateOfBirth=" + dateOfBirth + ", holderName=" + holderName
                + ", initialDeposit=" + initialDeposit + ", transactionFee=" + transactionFee + ", updatedAt="
                + updatedAt + "]";
    }
}
