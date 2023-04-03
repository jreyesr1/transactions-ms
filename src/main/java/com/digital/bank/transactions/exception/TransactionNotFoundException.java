package com.digital.bank.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Transaction was not found";

    public TransactionNotFoundException() {
        super(MESSAGE);
    }
}
