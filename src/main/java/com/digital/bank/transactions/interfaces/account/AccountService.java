package com.digital.bank.transactions.interfaces.account;

import com.digital.bank.transactions.model.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account updateBalance(Account account, BigDecimal amount);
    Account getAccountByIban(String accountIban);
}
