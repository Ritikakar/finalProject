package com.RAM.finalProject.Withdrawal;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RAM.finalProject.Account.Account;
import com.RAM.finalProject.Account.AccountRepository;

@Service
public class WithdrawalService {

    

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WithdrawalRepository withdrawalRepository;

     public Withdrawal createWithdrawal(Withdrawal withdrawal) {
        Account account = accountRepository.findById(withdrawal.getAccount()).orElse(null);
        if (account != null && account.getBalance() >= withdrawal.getAmount()) {
            account.setBalance(account.getBalance() - withdrawal.getAmount());  // Subtract withdrawal from balance
            accountRepository.save(account);
            withdrawal.setWithdrawalDate(new Date());
            return withdrawalRepository.save(withdrawal);
        }
        return null;
    }
    
}


