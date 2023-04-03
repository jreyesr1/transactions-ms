package com.digital.bank.transactions.util.account.balance;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BalanceCalculator {
    public BigDecimal calculateNewBalance(BigDecimal currentBalance, BigDecimal transactionAmount, BigDecimal fee) {
        BigDecimal amountWithFee = transactionAmount.subtract(fee);
        return currentBalance.add(amountWithFee);
    }
}
