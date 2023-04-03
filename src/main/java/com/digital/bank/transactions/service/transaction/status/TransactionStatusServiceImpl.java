package com.digital.bank.transactions.service.transaction.status;

import com.digital.bank.transactions.interfaces.transaction.TransactionRetriever;
import com.digital.bank.transactions.interfaces.transaction.TransactionStatusService;
import com.digital.bank.transactions.model.PostTransactionStatusRequest;
import com.digital.bank.transactions.model.Transaction;
import com.digital.bank.transactions.util.transaction.status.StatusCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionStatusServiceImpl implements TransactionStatusService {

    private final TransactionRetriever transactionRetriever;

    private final StatusCalculator statusCalculator;

    @Autowired
    public TransactionStatusServiceImpl(TransactionRetriever transactionRetriever,StatusCalculator statusCalculator) {
        this.transactionRetriever = transactionRetriever;
        this.statusCalculator = statusCalculator;
    }
    @Override
    public Transaction queryTransactionStatus(PostTransactionStatusRequest postTransactionStatusRequest) {
        String reference=postTransactionStatusRequest.getReference();
        PostTransactionStatusRequest.ChannelEnum channel=postTransactionStatusRequest.getChannel();
        Transaction transaction=transactionRetriever.getTransactionByReference(reference);
        if(transaction==null){
            transaction= Transaction.builder().reference(reference).build();
        }
        return statusCalculator.calcutateStatus(transaction,channel);
    }
}
