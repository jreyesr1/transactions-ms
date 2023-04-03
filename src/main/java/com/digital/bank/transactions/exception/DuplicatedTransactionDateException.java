package com.digital.bank.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DuplicatedTransactionDateException extends RuntimeException {
    private static final String MESSAGE = "A Transaction cannot have the same date";

    public DuplicatedTransactionDateException() {
        super(MESSAGE);
    }
}
