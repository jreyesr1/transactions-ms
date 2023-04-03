package com.digital.bank.transactions.interfaces.transaction;

import com.digital.bank.transactions.model.Account;
import com.digital.bank.transactions.model.Transaction;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRetriever {
    Transaction getTransactionByAccountAndDate(Account account, OffsetDateTime date);
    List<Transaction> getTransactionsByAccount(Account account, Optional<String> sortByAmount);

    Transaction  getTransactionByReference(String reference);
}
