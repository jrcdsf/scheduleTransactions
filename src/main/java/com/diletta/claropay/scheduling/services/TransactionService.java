package com.diletta.claropay.scheduling.services;

import com.diletta.claropay.scheduling.entities.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAll();
    Transaction findById(String transactionId);
    void save(Transaction theTransaction);
    void deleteById(String transactionId);
    List<Transaction> findByAccountId(String accountId);
}
