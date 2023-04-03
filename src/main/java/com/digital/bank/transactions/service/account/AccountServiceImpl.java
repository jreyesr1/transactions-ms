package com.digital.bank.transactions.service.account;

import com.digital.bank.transactions.interfaces.account.AccountRetriever;
import com.digital.bank.transactions.interfaces.account.AccountService;
import com.digital.bank.transactions.interfaces.account.AccountUpdater;
import com.digital.bank.transactions.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountUpdater accountUpdater;
    private final AccountRetriever accountRetriever;

    @Autowired
    public AccountServiceImpl(AccountUpdater accountUpdater, AccountRetriever accountRetriever) {
        this.accountUpdater = accountUpdater;
        this.accountRetriever = accountRetriever;
    }

    @Override
    public Account updateBalance(Account account, BigDecimal amount) {
        accountUpdater.updateBalance(account, amount);
        return account;
    }

    @Override
    public Account getAccountByIban(String accountIban) {
        return accountRetriever.getAccountByIban(accountIban);
    }
}
