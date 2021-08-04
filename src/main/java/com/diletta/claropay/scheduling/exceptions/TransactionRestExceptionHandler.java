package com.diletta.claropay.scheduling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<TransactionErrorResponse> handleException(TransactionNotFoundException transactionNotFoundException){
        TransactionErrorResponse transactionErrorResponse = new TransactionErrorResponse();
        transactionErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        transactionErrorResponse.setMessage(transactionNotFoundException.getMessage());
        transactionErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(transactionErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TransactionErrorResponse> handleException(Exception ex){
        TransactionErrorResponse transactionErrorResponse = new TransactionErrorResponse();
        transactionErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        transactionErrorResponse.setMessage(ex.getMessage());
        transactionErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(transactionErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
