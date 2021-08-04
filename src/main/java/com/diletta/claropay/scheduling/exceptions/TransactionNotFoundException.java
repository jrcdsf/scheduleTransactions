package com.diletta.claropay.scheduling.exceptions;

public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(Throwable cause) {
        super(cause);
    }

}
