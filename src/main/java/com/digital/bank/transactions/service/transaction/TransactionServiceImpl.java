package com.digital.bank.transactions.service.transaction;

import com.digital.bank.transactions.exception.BalanceBellowZeroException;
import com.digital.bank.transactions.exception.DuplicatedTransactionDateException;
import com.digital.bank.transactions.exception.ExistingTransactionReferenceException;
import com.digital.bank.transactions.interfaces.account.AccountService;
import com.digital.bank.transactions.interfaces.transaction.TransactionRetriever;
import com.digital.bank.transactions.interfaces.transaction.TransactionService;
import com.digital.bank.transactions.model.Account;
import com.digital.bank.transactions.model.CreateTransactionRequest;
import com.digital.bank.transactions.model.Transaction;
import com.digital.bank.transactions.repository.TransactionRepository;
import com.digital.bank.transactions.util.account.balance.BalanceCalculator;
import liquibase.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService;

    private final TransactionRepository transactionRepository;
    private final TransactionRetriever transactionRetriever;
    private final BalanceCalculator balanceCalculator;


    @Autowired
    public TransactionServiceImpl(AccountService accountService, TransactionRepository transactionRepository,TransactionRetriever transactionRetriever,BalanceCalculator balanceCalculator) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
        this.transactionRetriever = transactionRetriever;
        this.balanceCalculator = balanceCalculator;
    }

    @Override
    public Transaction createTransaction(CreateTransactionRequest createTransactionRequest) {
        Account account = accountService.getAccountByIban(createTransactionRequest.getAccountIban());
        if(transactionRetriever.getTransactionByAccountAndDate(account,createTransactionRequest.getDate())!=null){
            throw new DuplicatedTransactionDateException();
        }

        BigDecimal fee = (createTransactionRequest.getFee() != null) ? createTransactionRequest.getFee().abs() : BigDecimal.ZERO;
        BigDecimal accountBalance = account.getBalance();
        BigDecimal transactionAmount = createTransactionRequest.getAmount();
        BigDecimal newBalance = balanceCalculator.calculateNewBalance(accountBalance, transactionAmount, fee);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BalanceBellowZeroException();
        }
        String transactionReference = createTransactionRequest.getReference();
        if (StringUtil.isEmpty(transactionReference)) {
            String epochTime = String.valueOf(Instant.now().getEpochSecond());
            transactionReference = epochTime + "_" + createTransactionRequest.getAccountIban();
        } else {
            Optional<Transaction> transaction = transactionRepository.findByReference(transactionReference);
            if (!transaction.isEmpty())
                throw new ExistingTransactionReferenceException();
        }
        OffsetDateTime transactionDate = OffsetDateTime.now(ZoneId.of("Europe/Madrid"));

        if (createTransactionRequest.getDate() != null) {
            transactionDate = createTransactionRequest.getDate();

        }
        accountService.updateBalance(account, newBalance);

        Transaction transaction = Transaction.builder()
                .reference(transactionReference)
                .date(transactionDate)
                .amount(transactionAmount)
                .fee(fee)
                .description(createTransactionRequest.getDescription())
                .account(account)
                .build();

        transactionRepository.save(transaction);
        return transaction;

    }

    @Override
    public List<Transaction> findTransactionByIban(String accountIban,Optional<String>sortByAmount) {
        Account account = accountService.getAccountByIban(accountIban);
        return transactionRetriever.getTransactionsByAccount(account,sortByAmount);
    }

}
