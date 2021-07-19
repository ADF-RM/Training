package com.naveen.project.adf_bank.helpers;

import java.time.LocalDate;

public class CreateRequest {
    private String holderName;
    private LocalDate dateOfBirth;
    private String accountType;
    private Double initialDeposit = 0.0;

    public String getHolderName() {
        return holderName;
    }
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public Double getInitialDeposit() {
        return initialDeposit;
    }
    public void setInitialDeposit(Double initialDeposit) {
        this.initialDeposit = initialDeposit;
    }
    
}
