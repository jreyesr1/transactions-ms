package com.digital.bank.transactions.interfaces.transaction;

import com.digital.bank.transactions.model.Account;
import com.digital.bank.transactions.model.CreateTransactionRequest;
import com.digital.bank.transactions.model.PostTransactionStatusRequest;
import com.digital.bank.transactions.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(CreateTransactionRequest createTransactionRequest);

    List<Transaction> findTransactionByIban(String accountIban, Optional<String> sortByAmount);

}
