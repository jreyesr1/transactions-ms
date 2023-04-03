package com.digital.bank.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BalanceBellowZeroException extends RuntimeException {

    private static final String MESSAGE = "Transactions with  a result of Balance bellow zero are not allowed";
    public BalanceBellowZeroException() {
        super(MESSAGE);
    }

}