package com.digital.bank.transactions.interfaces.account;

import com.digital.bank.transactions.model.Account;

public interface AccountRetriever {
    Account getAccountByIban(String accountIban);
}
