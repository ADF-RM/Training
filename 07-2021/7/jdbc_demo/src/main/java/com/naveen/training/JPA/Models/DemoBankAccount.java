package com.naveen.training.JPA.Models;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DemoBankAccount {
    @Id
    private BigInteger ACCOUNT_NO;
    private String ACCOUNT_TYPE;
    private int TRANSACTION_FEE;
    private String HOLDER_NAME;
    
    public BigInteger getACCOUNT_NO() {
        return ACCOUNT_NO;
    }
    public void setACCOUNT_NO(BigInteger aCCOUNT_NO) {
        ACCOUNT_NO = aCCOUNT_NO;
    }
    public String getACCOUNT_TYPE() {
        return ACCOUNT_TYPE;
    }
    public void setACCOUNT_TYPE(String aCCOUNT_TYPE) {
        ACCOUNT_TYPE = aCCOUNT_TYPE;
    }
    public int getTRANSACTION_FEE() {
        return TRANSACTION_FEE;
    }
    public void setTRANSACTION_FEE(int tRANSACTION_FEE) {
        TRANSACTION_FEE = tRANSACTION_FEE;
    }
    public String getHOLDER_NAME() {
        return HOLDER_NAME;
    }
    public void setHOLDER_NAME(String hOLDER_NAME) {
        HOLDER_NAME = hOLDER_NAME;
    }

    @Override
    public String toString() {
        return "DemoBankAccount [ACCOUNT_NO=" + ACCOUNT_NO + ", ACCOUNT_TYPE=" + ACCOUNT_TYPE + ", HOLDER_NAME="
                + HOLDER_NAME + ", TRANSACTION_FEE=" + TRANSACTION_FEE + "]";
    }

}
