package com.RAM.finalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    // --------------------- Customer Endpoints --------------------- //

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = bankService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = bankService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = bankService.createCustomer(customer);
        return ResponseEntity.status(201).body(createdCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        bankService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    // --------------------- Account Endpoints --------------------- //

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = bankService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/accounts/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable String accountNumber) {
        Account account = bankService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = bankService.createAccount(account);
        return ResponseEntity.status(201).body(createdAccount);
    }

    @DeleteMapping("/accounts/{accountNumber}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountNumber) {
        bankService.deleteAccount(accountNumber);
        return ResponseEntity.noContent().build();
    }

    // --------------------- Transaction Endpoints --------------------- //

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = bankService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = bankService.createTransaction(transaction.getSourceAccountNumber() , transaction.getTargetAccountNumber(), transaction.getAmount());
        return ResponseEntity.status(201).body(createdTransaction);
    }

    @GetMapping("/transactions/account/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable String accountNumber) {
        List<Transaction> transactions = bankService.getTransactionsByAccount(accountNumber);
        return ResponseEntity.ok(transactions);
    }

    // --------------------- Deposit Endpoints --------------------- //

    @PostMapping("/deposits")
    public ResponseEntity<Deposit> createDeposit(@RequestBody Deposit deposit) {
        Deposit createdDeposit = bankService.createDeposit(deposit);
        return ResponseEntity.status(201).body(createdDeposit);
    }

    // --------------------- Withdrawal Endpoints --------------------- //

    @PostMapping("/withdrawals")
    public ResponseEntity<Withdrawal> createWithdrawal(@RequestBody Withdrawal withdrawal) {
        Withdrawal createdWithdrawal = bankService.createWithdrawal(withdrawal);
        return ResponseEntity.status(201).body(createdWithdrawal);
    }

    // --------------------- Loan Endpoints --------------------- //

    @PostMapping("/loans")
    public ResponseEntity<Loan> applyForLoan(@RequestParam Long customerId,
                                              @RequestParam double loanAmount,
                                              @RequestParam double interestRate,
                                              @RequestParam int loanTermYears) {
        Loan loan = bankService.applyForLoan(customerId, loanAmount, interestRate, loanTermYears);
        return ResponseEntity.status(201).body(loan);
    }

    @GetMapping("/loans/customer/{customerId}")
    public ResponseEntity<List<Loan>> getLoansByCustomer(@PathVariable Long customerId) {
        List<Loan> loans = bankService.getLoansByCustomer(customerId);
        return ResponseEntity.ok(loans);
    }
}
