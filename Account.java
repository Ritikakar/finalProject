package com.RAM.finalProject;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "account_number")
    private String accountNumber;

     @JsonProperty("customerId")  
    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private String accountType;


  
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

     
     public Account() {
    }

    public Account(String accountNumber, int customerId, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = balance;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", customerId=" + customerId +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
