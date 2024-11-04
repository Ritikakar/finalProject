package com.RAM.finalProject.Transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RAM.finalProject.Account.Account;
import com.RAM.finalProject.Account.AccountRepository;

import com.RAM.finalProject.Deposit.Deposit;
import com.RAM.finalProject.Deposit.DepositService;
import com.RAM.finalProject.Withdrawal.Withdrawal;
import com.RAM.finalProject.Withdrawal.WithdrawalService;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;


    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private DepositService depositService;


    @Autowired
    private AccountRepository accountRepository;


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Transaction transaction) {

        return transactionRepository.save(transaction);
    }

    public Transaction createTransaction(String sourceAccountNumber, String targetAccountNumber, double amount) {
        
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAccount(sourceAccountNumber);
        withdrawal.setAmount(amount);
        withdrawal.setWithdrawalDate(new Date());
     
        Withdrawal withdrawalResult = withdrawalService.createWithdrawal(withdrawal);
        
       
        if (withdrawalResult != null) {
          
            Deposit deposit = new Deposit();
            deposit.setAccount(targetAccountNumber);
            deposit.setAmount(amount);
    
         
            deposit.setDepositDate(new Date()); 
            Deposit depositResult = depositService.createDeposit(deposit);
    
            if (depositResult != null) {
                Transaction transaction = new Transaction();
                transaction.setSourceAccountNumber(sourceAccountNumber);
                transaction.setTargetAccountNumber(targetAccountNumber);
                transaction.setAmount(amount);
                transaction.setTransactionDate(new Date());
                return transactionRepository.save(transaction);
            }
        }
        return null; 
    }
    

    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        Account account = accountRepository.findById(accountNumber).orElse(null);
        if (account != null) {
            return transactionRepository.findAll();  // Here, you can implement a method to find transactions by account
        }
        return null;
    }

}
