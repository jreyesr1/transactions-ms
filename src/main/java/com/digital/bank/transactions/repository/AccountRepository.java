package com.digital.bank.transactions.repository;

import com.digital.bank.transactions.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountIban(String accountIban);
    @Modifying
    @Query("UPDATE Account a SET a.balance = ?1 WHERE a.id = ?2")
    void updateBalance(BigDecimal balance, Long id);
}
