package com.digital.bank.transactions.service.account;

import com.digital.bank.transactions.exception.AccountNotFoundException;
import com.digital.bank.transactions.interfaces.account.AccountRetriever;
import com.digital.bank.transactions.model.Account;
import com.digital.bank.transactions.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRetrieverImpl implements AccountRetriever {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountRetrieverImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountByIban(String accountIban) {
        return accountRepository.findByAccountIban(accountIban).orElseThrow(() ->new AccountNotFoundException());
    }
}
