package com.RAM.finalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class BankService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final LoanRepository loanRepository;
    private final DepositRepository depositRepository;
    private final WithdrawalRepository withdrawalRepository;

    @Autowired
    public BankService(
            CustomerRepository customerRepository,
            AccountRepository accountRepository,
            TransactionRepository transactionRepository,
            LoanRepository loanRepository,
            DepositRepository depositRepository,
            WithdrawalRepository withdrawalRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.loanRepository = loanRepository;
        this.depositRepository = depositRepository;
        this.withdrawalRepository = withdrawalRepository;
    }

    // --------------------- Customer Services --------------------- //

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public boolean existsCustomerById(Long id) {
        return customerRepository.existsById(id);
    }

    // --------------------- Account Services --------------------- //

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

    // --------------------- Transaction Services --------------------- //

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // public Transaction createTransaction(Transaction transaction) {

    //     return transactionRepository.save(transaction);
    // }

    public Transaction createTransaction(String sourceAccountNumber, String targetAccountNumber, double amount) {
        
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAccount(sourceAccountNumber);
        withdrawal.setAmount(amount);
        withdrawal.setWithdrawalDate(new Date());
     
        Withdrawal withdrawalResult = createWithdrawal(withdrawal);
        
       
        if (withdrawalResult != null) {
          
            Deposit deposit = new Deposit();
            deposit.setAccount(targetAccountNumber);
            deposit.setAmount(amount);
    
         
            deposit.setDepositDate(new Date()); 
            Deposit depositResult = createDeposit(deposit);
    
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

    // --------------------- Deposit Services --------------------- //

    public Deposit createDeposit(Deposit deposit) {
        Account account = accountRepository.findById(deposit.getAccount()).orElse(null);
        if (account != null) {
            account.setBalance(account.getBalance() + deposit.getAmount());  // Add deposit to balance
            accountRepository.save(account);
            deposit.setDepositDate(new Date()); 
            return depositRepository.save(deposit);
        }
        return null;
    }

    // --------------------- Withdrawal Services --------------------- //

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

    // --------------------- Loan Services --------------------- //

    public Loan applyForLoan(Long customerId, double loanAmount, double interestRate, int loanTermYears) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            Loan loan = new Loan();
            loan.setCustomer(customer);
            loan.setLoanAmount(loanAmount);
            loan.setInterestRate(interestRate);
            loan.setLoanTermYears(loanTermYears);
            loan.setLoanStatus("Pending");

            return loanRepository.save(loan);
        }
        return null;
    }

    public List<Loan> getLoansByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            return loanRepository.findAll();  // Implement a method to find loans by customer
        }
        return null;
    }
}
