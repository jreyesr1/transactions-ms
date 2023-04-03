package com.digital.bank.transactions.interfaces.account;

import com.digital.bank.transactions.model.Account;

import java.math.BigDecimal;

public interface AccountUpdater {
    void updateBalance(Account account, BigDecimal balance);
}
