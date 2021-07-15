package com.naveen.project.adf_bank.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Transactions {
    @Id
    @GeneratedValue
    private Integer transactionId;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="accountNo")
    private BankAccount bankAccount;
    private Double transactionAmount;
    private String transactionType = "DEPOSIT";
    private Double oldBalance = 0.0;
    private Double newBalance = 0.0;
    @CreationTimestamp
    private LocalDate transactionDate;
    private String transactionStatus;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Integer getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
    public Double getTransactionAmount() {
        return transactionAmount;
    }
    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getTransactionStatus() {
        return transactionStatus;
    }
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
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
    public BankAccount getBankAccount() {
        return bankAccount;
    }
    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
    public Double getOldBalance() {
        return oldBalance;
    }
    public void setOldBalance(Double oldBalance) {
        this.oldBalance = oldBalance;
    }
    public Double getNewBalance() {
        return newBalance;
    }
    public void setNewBalance(Double newBalance) {
        this.newBalance = newBalance;
    }
    
    @Override
    public String toString() {
        return "Transactions [bankAccount=" + bankAccount + ", createdAt=" + createdAt + ", newBalance=" + newBalance
                + ", oldBalance=" + oldBalance + ", transactionAmount=" + transactionAmount + ", transactionDate="
                + transactionDate + ", transactionId=" + transactionId + ", transactionStatus=" + transactionStatus
                + ", transactionType=" + transactionType + ", updatedAt=" + updatedAt + "]";
    }   
}
