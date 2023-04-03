package com.digital.bank.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ExistingTransactionReferenceException extends RuntimeException {

    private static final String MESSAGE = "Transactions reference already exists";
    public ExistingTransactionReferenceException() {
        super(MESSAGE);
    }

}