package com.digital.bank.transactions;

import com.digital.bank.transactions.util.account.balance.BalanceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class BalanceCalculatorTest {

    @Autowired
    private BalanceCalculator balanceCalculator;

    @Test
    public void testCalculateNewBalance_shouldReturnExpectedResult() {
        BigDecimal currentBalance = new BigDecimal("100");
        BigDecimal transactionAmount = new BigDecimal("50");
        BigDecimal fee = new BigDecimal("2");

        BigDecimal newBalance = balanceCalculator.calculateNewBalance(currentBalance, transactionAmount, fee);

        Assertions.assertEquals(new BigDecimal("148"), newBalance);
    }

}