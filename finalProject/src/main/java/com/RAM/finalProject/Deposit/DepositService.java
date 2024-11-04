package com.RAM.finalProject.Deposit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RAM.finalProject.Account.Account;
import com.RAM.finalProject.Account.AccountRepository;


import java.util.Date;

@Service
public class DepositService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private DepositRepository depositRepository;

      public Deposit createDeposit(Deposit deposit) {
        System.out.println(deposit.getAccount() + " " + deposit.getDepositDate());
        Account account = accountRepository.findById(deposit.getAccount()).orElse(null);
        if (account != null) {
            account.setBalance(account.getBalance() + deposit.getAmount());  
            accountRepository.save(account);
            deposit.setDepositDate(new Date()); 
            System.out.println(deposit.getAccount() + " " + deposit.getDepositDate());
            return depositRepository.save(deposit);
        }
        return null;
    }
    
}
