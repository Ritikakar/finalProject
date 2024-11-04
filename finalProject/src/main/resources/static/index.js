document.querySelectorAll('.nav-links a').forEach(link => {
    link.addEventListener('click', function(e) {
        e.preventDefault(); // Prevent default anchor behavior
        
        // Get the href of the clicked link
        const targetId = this.getAttribute('href'); // e.g., "#Manage_Customers"

        // Hide all sections
        document.querySelectorAll('section').forEach(section => {
            section.style.display = 'none';
        });

        // Show the target section
        const targetSection = document.querySelector(targetId);
        if (targetSection) {
            targetSection.style.display = 'block';
        }
    });
});




        function createTable(data) {
            let table = '<table><thead><tr>';
            Object.keys(data[0]).forEach(key => {
                table += `<th>${key.charAt(0).toUpperCase() + key.slice(1)}</th>`;
            });
            table += '</tr></thead><tbody>';
            data.forEach(item => {
                table += '<tr>';
                Object.values(item).forEach(value => {
                    table += `<td>${value}</td>`;
                });
                table += '</tr>';
            });
            table += '</tbody></table>';
            return table;
        }

//         function createTable(data) {
//     let table = '<table><thead><tr>';
//     Object.keys(data[0]).forEach(key => {
//         if (typeof data[0][key] === 'object' && data[0][key] !== null) {
//             table += `<th>${key.charAt(0).toUpperCase() + key.slice(1)} Id</th>`;
//             table += `<th>${key.charAt(0).toUpperCase() + key.slice(1)} Name</th>`;
//         } else {
//             table += `<th>${key.charAt(0).toUpperCase() + key.slice(1)}</th>`;
//         }
//     });
//     table += '</tr></thead><tbody>';
//     data.forEach(item => {
//         table += '<tr>';
//         Object.values(item).forEach(value => {
//             if (typeof value === 'object' && value !== null) {
//                 table += `<td>${value.id}</td>`;
//                 table += `<td>${value.name}</td>`;
//             } else {
//                 table += `<td>${value}</td>`;
//             }
//         });
//         table += '</tr>';
//     });
//     table += '</tbody></table>';
//     return table;
// }

function showToast(message) {
    console.log("message" , message);
    
    // Create a toast element
    const toast = document.createElement('div');
    toast.className = 'toast'; // Add toast class for styling
    toast.innerText = message; // Set the message

    // Append the toast to the body
    document.body.appendChild(toast);

    // Show the toast
    setTimeout(() => {
        toast.classList.add('show');
    }, 10); // Use a slight delay to allow for the CSS transition

    // Hide and remove the toast after 3 seconds
    setTimeout(() => {
        toast.classList.remove('show');
        // Remove the toast element from the DOM after the transition
        setTimeout(() => {
            document.body.removeChild(toast);
        }, 300); // Match this timeout with the CSS transition duration
    }, 3000);
}

        // Customer Functions
        document.getElementById('customerForm').addEventListener('submit', function(event) {
            event.preventDefault();
            // const customerId = document.getElementById('customerId').value;
            const customerName = document.getElementById('customerName').value;
            const customerAddress = document.getElementById('customerAddress').value;
            const customerPhone = document.getElementById('customerPhone').value;
            const customerEmail = document.getElementById('customerEmail').value;

            fetch('/api/bank/customers', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    
                    name: customerName,
                    address: customerAddress,
                    phone: customerPhone,
                    email: customerEmail
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                showToast(data.name + "'s is added as customer")
                // document.getElementById('customerResult').innerHTML = JSON.stringify(data);
            })
            .catch(err => {
                document.getElementById('customerResult').innerHTML = `<div class="error">Error: ${err.message}</div>`;
            });
        });

        document.getElementById('getAllCustomers').addEventListener('click', function() {
            fetch('/api/bank/customers')
            .then(response => response.json())
            .then(data => {
                document.getElementById('customerResult').innerHTML = createTable(data);
            })
            .catch(err => {
                document.getElementById('customerResult').innerHTML = `<div class="error">Error: ${err}</div>`;
            });
        });

        // Account Functions
        document.getElementById('accountForm').addEventListener('submit', function(event) {
            event.preventDefault();
            const accountNumber = document.getElementById('accountNumber').value;
            const customerId = parseInt(document.getElementById('customerAccountId').value, 10);

            const accountType = document.getElementById('accountType').value;
            const balance = 0;

            console.log(customerId);


            fetch('/api/bank/accounts', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ accountNumber : accountNumber, customerId: customerId,accountType: accountType,balance: balance})
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                showToast(data.accountNumber + " is created successfully")
                // document.getElementById('accountResult').innerHTML = JSON.stringify(data);
            })
            .catch(err => {
                document.getElementById('accountResult').innerHTML = `<div class="error">Error: ${err.message}</div>`;
            });
        });

        document.getElementById('getAllAccounts').addEventListener('click', function() {
            fetch('/api/bank/accounts')
            .then(response => response.json())
            .then(data => {
                console.log(data);
                document.getElementById('accountResult').innerHTML = createTable(data);
            })
            .catch(err => {
                document.getElementById('accountResult').innerHTML = `<div class="error">Error: ${err}</div>`;
            });
        });

        // Deposit Functions
        document.getElementById('depositForm').addEventListener('submit', function(event) {
            event.preventDefault();
            const accountNumber = document.getElementById('depositAccountNumber').value;
            const amount = document.getElementById('depositAmount').value;

            fetch('/api/bank/deposits', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ account:  accountNumber ,amount: amount })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log(data.amount + " is added to account number: " + data.account );
                
                showToast(data.amount + "₹ have been deposited to account: " + data.account )
                // document.getElementById('depositResult').innerHTML = JSON.stringify(data);
            })
            .catch(err => {
                document.getElementById('depositResult').innerHTML = `<div class="error">Error: ${err.message}</div>`;
            });
        });

        // Withdrawal Functions
        document.getElementById('withdrawalForm').addEventListener('submit', function(event) {
            event.preventDefault();
            const accountNumber = document.getElementById('withdrawalAccountNumber').value;
            const amount = document.getElementById('withdrawalAmount').value;

            fetch('/api/bank/withdrawals', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ account: accountNumber ,amount: amount })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                showToast(data.amount + "₹ have been withdrawn from account: " + data.account )
                // document.getElementById('withdrawalResult').innerHTML = JSON.stringify(data);
            })
            .catch(err => {
                document.getElementById('withdrawalResult').innerHTML = `<div class="error">Error: ${err.message}</div>`;
            });
        });

        document.getElementById('transactionForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const sourceAccountNumber = document.getElementById('sourceAccountNumber').value;
    const targetAccountNumber = document.getElementById('targetAccountNumber').value;
    const transactionAmount = document.getElementById('transactionAmount').value;

    fetch('/api/bank/transactions', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ 
            sourceAccountNumber: sourceAccountNumber,
            targetAccountNumber: targetAccountNumber,
            amount: transactionAmount 
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        showToast("Transaction successful! " + transactionAmount + "₹ transferred from account " + sourceAccountNumber + " to " + targetAccountNumber);
        
        // document.getElementById('transactionResult').innerHTML = "Transaction successful: " + JSON.stringify(data);
    })
    .catch(err => {
        document.getElementById('transactionResult').innerHTML = `<div class="error">Error: ${err.message}</div>`;
    });
});

        // Loan Functions
        document.getElementById('loanForm').addEventListener('submit', function(event) {
            event.preventDefault();
            const customerId = document.getElementById('loanCustomerId').value;
            const loanAmount = document.getElementById('loanAmount').value;
            const interestRate = document.getElementById('interestRate').value;
            const loanTerm = document.getElementById('loanTerm').value;

            fetch('/api/bank/loans', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ customerId, loanAmount, interestRate, loanTerm })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                document.getElementById('loanResult').innerHTML = JSON.stringify(data);
            })
            .catch(err => {
                document.getElementById('loanResult').innerHTML = `<div class="error">Error: ${err.message}</div>`;
            });
        });

        document.getElementById('getLoansByCustomer').addEventListener('click', function() {
            const customerId = document.getElementById('loanCustomerId').value;
            fetch(`/api/bank/loans/customer/${customerId}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('loanResult').innerHTML = createTable(data);
            })
            .catch(err => {
                document.getElementById('loanResult').innerHTML = `<div class="error">Error: ${err}</div>`;
            });
        });
   