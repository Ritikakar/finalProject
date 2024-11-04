package com.RAM.finalProject.Deposit;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bank")
public class DepositController {

    @Autowired
    private DepositService depositService;




     @PostMapping("/deposits")
    public ResponseEntity<Deposit> createDeposit(@RequestBody Deposit deposit) {
        Deposit createdDeposit = depositService.createDeposit(deposit);
        return ResponseEntity.status(201).body(createdDeposit);
    }
    
}
