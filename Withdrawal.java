package com.RAM.finalProject;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "withdrawals")
public class Withdrawal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long withdrawalId;

    
    @JoinColumn(name = "account_number", nullable = false)
    private String account;

    @Column(nullable = false)
    private double amount;

    @Column(name = "withdrawal_date", nullable = false)
    private Date withdrawalDate;


    // Default constructor
    public Withdrawal() {
    }

    // Parameterized constructor
    public Withdrawal(String account, double amount, Date withdrawalDate) {
        this.account = account;
        this.amount = amount;
        this.withdrawalDate = withdrawalDate;
    }

    // Getters and Setters
    public Long getWithdrawalId() {
        return withdrawalId;
    }

    public void setWithdrawalId(Long withdrawalId) {
        this.withdrawalId = withdrawalId;
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

    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }
}
