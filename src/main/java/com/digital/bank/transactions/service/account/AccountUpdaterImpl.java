package com.digital.bank.transactions.service.account;

import com.digital.bank.transactions.interfaces.account.AccountUpdater;
import com.digital.bank.transactions.model.Account;
import com.digital.bank.transactions.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountUpdaterImpl implements AccountUpdater {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountUpdaterImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void updateBalance(Account account, BigDecimal balance) {
        account.setBalance(balance);
        accountRepository.save(account);
    }
}
