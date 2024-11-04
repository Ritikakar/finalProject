package com.RAM.finalProject.Account;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findById(accountNumber).orElse(null);
    }

    public Account createAccount(Account account) {
        System.out.println(account.getAccountNumber() + " " + account.getAccountType()+ " " + account.getBalance()+ " " + account.getCustomerId());
        // Account acc = new Account(account.getAccountNumber() , )
        return accountRepository.save(account);
    }

    public void deleteAccount(String accountNumber) {
        accountRepository.deleteById(accountNumber);
    }

    public boolean existsAccountByNumber(String accountNumber) {
        return accountRepository.existsById(accountNumber);
    }

    
}
