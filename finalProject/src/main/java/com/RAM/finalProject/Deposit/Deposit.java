package com.RAM.finalProject.Deposit;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;

    
    @Column(name = "account_number", nullable = false)
    private String account;

    @Column(nullable = false)
    private double amount;

    @Column(name = "deposit_date", nullable = false)
    private Date depositDate;

    // Getters and Setters
    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    // Default no-argument constructor (required by JPA)
    public Deposit() {}

    // Parameterized constructor
    public Deposit(String account, double amount, Date depositDate) {
        this.account = account;
        this.amount = amount;
        this.depositDate = depositDate;
    }

    
}
