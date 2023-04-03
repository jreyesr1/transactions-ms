package com.digital.bank.transactions;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.digital.bank.transactions.interfaces.transaction.TransactionRetriever;
import com.digital.bank.transactions.interfaces.transaction.TransactionStatusService;
import com.digital.bank.transactions.model.PostTransactionStatusRequest;
import com.digital.bank.transactions.model.Transaction;
import com.digital.bank.transactions.service.transaction.status.TransactionStatusServiceImpl;
import com.digital.bank.transactions.util.transaction.status.StatusCalculator;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class TransactionStatusServiceImplTest {

    private TransactionStatusService transactionStatusService;

    private TransactionRetriever transactionRetriever;

    private StatusCalculator statusCalculator;

    @BeforeEach
    public void setUp() {
        transactionRetriever = mock(TransactionRetriever.class);
        statusCalculator = mock(StatusCalculator.class);
        transactionStatusService = new TransactionStatusServiceImpl(transactionRetriever, statusCalculator);
    }

    @Test
    public void testQueryTransactionStatus_withValidTransactionAndChannel_shouldReturnExpectedResult() {
        String reference = "123456";
        PostTransactionStatusRequest.ChannelEnum channel = PostTransactionStatusRequest.ChannelEnum.CLIENT;
        BigDecimal amount = BigDecimal.valueOf(1000);
        BigDecimal fee = BigDecimal.valueOf(5);
        Transaction transaction = Transaction.builder()
                .reference(reference)
                .date(OffsetDateTime.now().minusDays(1))
                .status(Transaction.StatusEnum.PENDING)
                .amount(amount)
                .fee(fee)
                .build();
        Transaction expectedTransaction = Transaction.builder()
                .reference(reference)
                .date(transaction.getDate())
                .status(Transaction.StatusEnum.SETTLED)
                .amount(amount.subtract(fee))
                .build();
        when(transactionRetriever.getTransactionByReference(reference)).thenReturn(transaction);
        when(statusCalculator.calcutateStatus(transaction, channel)).thenReturn(expectedTransaction);

        Transaction result = transactionStatusService.queryTransactionStatus(
                PostTransactionStatusRequest.builder().reference(reference).channel(channel).build());

        assertEquals(expectedTransaction, result);
    }

}
