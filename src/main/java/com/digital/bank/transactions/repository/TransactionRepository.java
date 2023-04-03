package com.digital.bank.transactions.repository;

import com.digital.bank.transactions.model.Account;
import com.digital.bank.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByReference(String accountIban);
    Optional<Transaction> findByAccountAndDate(Account account, OffsetDateTime date);

    List<Transaction> findByAccountOrderByDateDesc(Account account);
    List<Transaction> findByAccountOrderByAmountAsc(Account account);
    List<Transaction> findByAccountOrderByAmountDesc(Account account);

}
