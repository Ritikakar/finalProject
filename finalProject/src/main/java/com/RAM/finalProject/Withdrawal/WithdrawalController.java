package com.RAM.finalProject.Withdrawal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;


      @PostMapping("/withdrawals")
    public ResponseEntity<Withdrawal> createWithdrawal(@RequestBody Withdrawal withdrawal) {
        Withdrawal createdWithdrawal = withdrawalService.createWithdrawal(withdrawal);
        return ResponseEntity.status(201).body(createdWithdrawal);
    }
    
}
