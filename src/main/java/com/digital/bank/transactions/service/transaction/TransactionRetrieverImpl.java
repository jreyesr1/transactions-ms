package com.digital.bank.transactions.service.transaction;

import com.digital.bank.transactions.exception.TransactionNotFoundException;
import com.digital.bank.transactions.interfaces.transaction.TransactionRetriever;
import com.digital.bank.transactions.model.Account;
import com.digital.bank.transactions.model.Transaction;
import com.digital.bank.transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionRetrieverImpl implements TransactionRetriever {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionRetrieverImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Override
    public Transaction getTransactionByAccountAndDate(Account account, OffsetDateTime date) {
        Transaction transaction=transactionRepository.findByAccountAndDate(account,date).orElseGet(() -> null);
        return transaction;
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Account account, Optional<String> sortByAmount) {
        switch (sortByAmount.orElse("")) {
            case "asc" -> {
                return transactionRepository.findByAccountOrderByAmountAsc(account);
            }
            case "desc" ->{
                return  transactionRepository.findByAccountOrderByAmountDesc(account);
            }
            default ->{
                return  transactionRepository.findByAccountOrderByDateDesc(account);
            }
        }
    }

    @Override
    public Transaction getTransactionByReference(String reference) {
        return transactionRepository.findByReference(reference).orElse(null);
    }
}
