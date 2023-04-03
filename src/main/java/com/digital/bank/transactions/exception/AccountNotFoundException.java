package com.digital.bank.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Account IBAN was not found";

    public AccountNotFoundException() {
        super(MESSAGE);
    }
}
