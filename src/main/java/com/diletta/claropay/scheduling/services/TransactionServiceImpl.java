package com.diletta.claropay.scheduling.services;

import com.diletta.claropay.scheduling.dao.TransactionRepository;
import com.diletta.claropay.scheduling.entities.Transaction;
import com.diletta.claropay.scheduling.exceptions.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAll() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    @Override
    public Transaction findById(String transactionId) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isPresent()) return transaction.get();
        throw new TransactionNotFoundException("Transaction Id not found - " + transactionId);
    }

    @Override
    public void save(Transaction theTransaction) {
        transactionRepository.save(theTransaction);

    }

    @Override
    public void deleteById(String transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public List<Transaction> findByAccountId(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
