package com.diletta.claropay.scheduling.services;

import com.diletta.claropay.scheduling.dao.TransactionRepository;
import com.diletta.claropay.scheduling.entities.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceMockTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService = new TransactionServiceImpl(transactionRepository);

    @Mock
    private List<Transaction> transactionListMock;

    @Mock
    private Transaction transactionMock;

    @BeforeEach
    void setupMock() {
        when(transactionRepository.findAll()).thenReturn(transactionListMock);
        when(transactionRepository.findById(any())).thenReturn(java.util.Optional.of(transactionMock));
    }

    @DisplayName("FindAll() should return all the transactions")
    @Test
    void testFindAll() {
        assertEquals(transactionListMock, transactionService.findAll());
    }

    @DisplayName("FindAll() should return a specific transaction")
    @Test
    void testFindById() {
        assertEquals(transactionMock, transactionService.findById(any()));
    }
}
