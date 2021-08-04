package com.diletta.claropay.scheduling.rest;

import com.diletta.claropay.scheduling.entities.Transaction;
import com.diletta.claropay.scheduling.exceptions.TransactionNotFoundException;
import com.diletta.claropay.scheduling.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class TransactionRestController {

    private TransactionService transactionService;

    @Autowired
    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/transactions/{transactionId}")
    public Transaction getTransaction(@PathVariable String transactionId) {
        Transaction transaction = transactionService.findById(transactionId);
        if (transaction == null) {
            throw new TransactionNotFoundException("Transaction Id not found - " + transactionId);
        }
        return transaction;
    }

    @PostMapping("/transactions")
    public Transaction addTransaction(@RequestBody Transaction theTransaction) {

        transactionService.save(theTransaction);
        return theTransaction;
    }

    @PutMapping("/transactions")
    public Transaction updateTransaction(@RequestBody Transaction theTransaction){
        transactionService.save(theTransaction);
        return theTransaction;
    }

    @DeleteMapping("/transactions/{transactionId}")
    public String deleteTransaction(@PathVariable String transactionId) {
        Transaction transaction = transactionService.findById(transactionId);
        if (Objects.equals(transactionId, "")) {
            throw new TransactionNotFoundException("Transaction Id not found - " + transactionId);
        }
        transactionService.deleteById(transactionId);
        return "Deleted transaction Id - " + transactionId;
    }

    @GetMapping("/transactions/account/{accountId}")
    public List<Transaction> findByAccountId(@PathVariable String accountId) {
        return transactionService.findByAccountId(accountId);
    }
}
