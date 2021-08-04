package com.diletta.claropay.scheduling.dao;

import com.diletta.claropay.scheduling.entities.Transaction;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface TransactionRepository extends CrudRepository<Transaction, String> {
    List<Transaction> findByAccountId(String accountId);
}
