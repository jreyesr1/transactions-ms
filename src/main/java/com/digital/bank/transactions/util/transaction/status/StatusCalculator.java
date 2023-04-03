package com.digital.bank.transactions.util.transaction.status;

import com.digital.bank.transactions.model.PostTransactionStatusRequest;
import com.digital.bank.transactions.model.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Component
public class StatusCalculator {

    public Transaction calcutateStatus(Transaction transaction, PostTransactionStatusRequest.ChannelEnum channel) {
        if(channel==null){
            return Transaction.builder().build();
        }
        if (transaction.getAccount() == null) {
            return applyRuleA(transaction);
        }

        if ((channel.equals(PostTransactionStatusRequest.ChannelEnum.CLIENT) || channel.equals(PostTransactionStatusRequest.ChannelEnum.ATM))
                && transactionIsOneDayBefore(transaction.getDate(), OffsetDateTime.now()))
        {
            return applyRuleB(transaction);
        }

        if(channel.equals(PostTransactionStatusRequest.ChannelEnum.INTERNAL) && transactionIsOneDayBefore(transaction.getDate(), OffsetDateTime.now())){
            return applyRuleC(transaction);
        }
        return Transaction.builder().build();
    }

    private Transaction applyRuleA(Transaction transaction) {
        transaction.setStatus(Transaction.StatusEnum.INVALID);
        return transaction;
    }

    private Transaction applyRuleB(Transaction transaction) {
        return Transaction.builder().reference(transaction.getReference()).status(Transaction.StatusEnum.SETTLED).amount(transaction.getAmount().subtract(transaction.getFee())).build();
    }

    private Transaction applyRuleC(Transaction transaction) {
        return Transaction.builder().reference(transaction.getReference()).status(Transaction.StatusEnum.SETTLED).amount(transaction.getAmount()).fee(transaction.getFee()).build();
    }

    private boolean transactionIsOneDayBefore(OffsetDateTime dateTime1, OffsetDateTime dateTime2) {
        LocalDate date1 = dateTime1.toLocalDate();
        LocalDate date2 = dateTime2.toLocalDate();
        return date1.plusDays(1).isEqual(date2);
    }

}
