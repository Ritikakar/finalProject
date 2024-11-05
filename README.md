## Banking Application - Spring Boot Backend

This project implements a sleek and efficient RESTful API for a simple banking application using Spring Boot. It provides functionalities for managing customers, accounts, deposits, withdrawals, and transactions, all wrapped in a clean and organized structure.

## ✨ Features

- **Customer Management:** Seamlessly create, retrieve, and delete customer records, including name, address, phone number, and email.
- **Account Management:** Effortlessly manage accounts, linking them to customers with an account number, balance, and specific account type.
- **Deposits:** Securely deposit funds into existing accounts, updating balances in real-time.
- **Withdrawals:** Withdraw funds from accounts while gracefully handling insufficient balance scenarios.
- **Transactions:** Execute smooth fund transfers between accounts, managing withdrawals and deposits concurrently.

## 🚀 Technologies Used

- **Java 17:** Powering the application's core logic with modern language features.
- **Spring Boot:** Building the robust and scalable RESTful API foundation.
- **Spring Data JPA:** Simplifying database interactions with intuitive repositories.
- **Hibernate:** Providing seamless Object-Relational Mapping (ORM) for efficient data management.
- **H2 Database:** Utilizing an in-memory database for streamlined development (easily swappable with other databases).
- **REST Controller:** Defining clear and concise API endpoints for easy integration.
- **Jackson:** Handling JSON serialization and deserialization for smooth data exchange.
- **Maven:** Managing dependencies and automating the build process for a smooth developer experience.

## 📂 Project Structure

The project adheres to a standard Spring Boot structure, promoting clarity and maintainability:

- **`com.RAM.finalProject`**: The root package encompassing the entire application.
- **`Account`**: Dedicated to account management, housing the entity, repository, service, and controller.
- **`Customer`**: Focused on customer management, containing the entity, repository, service, and controller.
- **`Deposit`**: Handling deposit operations, with its entity, repository, service, and controller.
- **`Withdrawal`**: Managing withdrawal operations, with its entity, repository, service, and controller.
- **`Transactions`**: Orchestrating transactions, with its entity, repository, service, and controller.

## 🔗 API Endpoints

### Customer Management

| Method | Endpoint                   | Description                 |
| ------ | -------------------------- | --------------------------- |
| GET    | `/api/bank/customers`      | Retrieves all customers.    |
| GET    | `/api/bank/customers/{id}` | Retrieves a customer by ID. |
| POST   | `/api/bank/customers`      | Creates a new customer.     |
| DELETE | `/api/bank/customers/{id}` | Deletes a customer by ID.   |

### Account Management

| Method | Endpoint                             | Description                             |
| ------ | ------------------------------------ | --------------------------------------- |
| GET    | `/api/bank/accounts`                 | Retrieves all accounts.                 |
| GET    | `/api/bank/accounts/{accountNumber}` | Retrieves an account by account number. |
| POST   | `/api/bank/accounts`                 | Creates a new account.                  |
| DELETE | `/api/bank/accounts/{accountNumber}` | Deletes an account by account number.   |

### Deposits

| Method | Endpoint             | Description                    |
| ------ | -------------------- | ------------------------------ |
| POST   | `/api/bank/deposits` | Makes a deposit to an account. |

### Withdrawals

| Method | Endpoint                | Description                         |
| ------ | ----------------------- | ----------------------------------- |
| POST   | `/api/bank/withdrawals` | Makes a withdrawal from an account. |

### Transactions

| Method | Endpoint                                         | Description                                     |
| ------ | ------------------------------------------------ | ----------------------------------------------- |
| GET    | `/api/bank/transactions`                         | Retrieves all transactions.                     |
| POST   | `/api/bank/transactions`                         | Creates a new transaction between two accounts. |
| GET    | `/api/bank/transactions/account/{accountNumber}` | Retrieves transactions for a specific account.  |

## ▶️ Running the Application

1. **Clone:** `https://github.com/Ritikakar/finalProject.git ` 
2. **Navigate:** `cd banking-application`
3. **Build:** `mvn clean install`
4. **Run:** `mvn spring-boot:run`

Access the frontend at `http://localhost:8081` .

## 🌱 Future Improvements

- **Enhanced Security:** Implement robust authentication and authorization mechanisms.
- **Data Validation:** Enforce strict input validation for data integrity.
- **Comprehensive Error Handling:** Provide detailed and user-friendly error messages.
- **Loan Management Module:** Integrate a full-fledged loan management system.
- **Advanced Transaction History:** Implement filtering, pagination, and search capabilities for transaction history.
- **Polished UI/UX:** Enhance the frontend for a more intuitive and engaging user experience.
- **Rigorous Testing:** Expand test coverage with comprehensive unit and integration tests.


## Description of the codes: 

Application Entry Point
FinalProjectApplication: This annotated with @SpringBootApplication class is the entry point to the Spring Boot application. 
Here, SpringApplication is called in the main method. run(FinalProjectApplication.class, args) launches the Spring app, does component scan, and do configurations.

Account Module
a) Account Entity
The Account class keeps the state of a bank account. This one is annotated with @Entity and specifies accounts table in the database. 
Important fields:

        accountNumber: Primary Key, unique account identifier.
        customerId: The ID of the customer this account belongs to.
        balance: The latest message balance on the account.
        accountType: Type of Account (Savings, Checking, etc.).
        
Consists of constructors, getters, and setters as well as toString.

b) AccountRepository
Interface extending JpaRepository<Account, String>. Spring Data JPA CRUD over Account entity.

c) AccountService
Business logic layer with @Service annotation. 
Important methods:

        getAllAccounts(): Fetches all the accounts.
        getAccountByNumber(String accountNumber): Searches for an account using its number.
        createAccount(Account account): Allows creating and saving a new account.
        deleteAccount(String accountNumber): Deletes an account by passing in account number.
        
d) AccountController
Uses @RestController annotation and provides endpoints for account-related operations.
Routes:

        GET /api/bank/accounts: Retrieves all accounts.
        GET /api/bank/accounts/{accountNumber}: Retrieve an individual account using the account number.
        POST /api/bank/accounts: To create an account.
        DELETE /api/bank/accounts/{accountNumber}: Remove account by number.
        
Customer Module
a) Customer Entity
Represents a customer class mapped with customers table. 
Key fields:

        id: Auto-generated ID, Primary Key.
        name, address, phone, and email: Fundamental information for the customer.
        
Contains constructors, getters, and setters.
b) CustomerRepository
Interface which extends JpaRepository<Customer, Long>. Provides CRUD operations for Customer entity.

c) CustomerService
Implemented with @Service, has business logic related to Customer. 
Primary functions:

        getAllCustomers(): Retrieves all the customers.
        getCustomerById(Long id): Retrieves a customer based on their ID.
        createCustomer(Customer customer): For adding a new customer.
        deleteCustomer(Long id): Deletes customer with a given ID.
d) CustomerController
Provides request mapping for Customer and is tagged with @RestController.
Routes:

        GET /api/bank/customers: Get All Customers.
        GET /api/bank/customers/{id}: Retrieves a customer with specified ID.
        POST /api/bank/customers: To create a new customer.
        DELETE /api/bank/customers/{id}: Remove a customer by ID.
        
Deposit Module
a) Deposit Entity
Represents a deposit transaction, mapped into deposits table.

        depositId: Identifier for the deposit.
        account: Number of deposit account.
        amount: Deposit amount.
        depositDate: Date of Deposit.
b) DepositRepository
Interface that extends JpaRepository<Deposit, Long>. Provides CRUD operations for Deposit entity.

c) DepositService
Annotated with @Service. This service class handles deposit operations.
createDeposit(Deposit deposit): Verifies if the account has been created, updates the account's balance, and finally saves the deposit transaction.

d) DepositController
Handles deposit requests and issues them via the DepositController.
Route: POST /api/bank/deposits: Create a new deposit.

Transaction Module
a) Transaction Entity
A Transaction between Accounts in a Bank, maps to transactions table. 
Key Fields:

        transactionId: Unique transaction ID.
        sourceAccountNumber: Sender's account number.
        targetAccountNumber: Recipient's account number.
        amount: Amount transferred.
        transactionDate: Date of transaction.
        
b) TransactionRepository
Interface extending JpaRepository<Transaction, Long>. Exposes CRUDs for Transaction entity.

c) TransactionService
The principal logic for creating transactions. 
Important functions:

        getAllTransactions(): Retrieves all transactions.
        createTransaction(...): Creates transaction transferring from source account to target account.
        getTransactionsByAccount(String accountNumber): Returns a list of transactions for a given account.
        
d) TransactionController
Handles HTTP requests for transactions.
Routes:

        GET /api/bank/transactions: Shows all transactions.
        POST /api/bank/transactions: Creates a transaction.
        GET /api/bank/transactions/account/{accountNumber}: Gets transactions of an account.
        
Withdrawal Module
a) Withdrawal Entity
Maps a withdrawal transaction to the withdrawals table.

        withdrawalId: Unique identifier for each withdrawal.
        account: Linked account number.
        amount: Withdrawn amount.
        withdrawalDate: Date of withdrawal.
        
b) WithdrawalRepository
Interface extending JpaRepository<Withdrawal, Long>. Provides CRUD operations for Withdrawal entity.

c) WithdrawalService
Responsible for processing withdrawal transactions. Main method:

createWithdrawal(Withdrawal withdrawal): Verifies sufficient funds, deducts the amount, and saves the withdrawal.
d) WithdrawalController
Handles HTTP requests for withdrawals.

Route: POST /api/bank/withdrawals: Create a new withdrawal.
Configuration and Dependencies

Database Configuration: Uses application.properties or application.yml for database settings.
Dependencies: List of Spring Boot Starter dependencies, Spring Data JPA, and other libraries defined in pom.xml.

# Contributors

Ashish Asnani
Ritika Kar
Maanvita Alampalli
