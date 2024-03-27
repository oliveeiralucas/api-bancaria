package com.oliveeiralucas.apibancaria.controller;

import com.oliveeiralucas.apibancaria.entity.Transaction;
import com.oliveeiralucas.apibancaria.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionService.create(transaction);
    }

    @GetMapping
    public List<Transaction> list(){
        return transactionService.list();
    }
}
