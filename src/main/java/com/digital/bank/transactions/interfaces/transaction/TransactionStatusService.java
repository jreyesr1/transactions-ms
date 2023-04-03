package com.digital.bank.transactions.interfaces.transaction;

import com.digital.bank.transactions.model.CreateTransactionRequest;
import com.digital.bank.transactions.model.PostTransactionStatusRequest;
import com.digital.bank.transactions.model.Transaction;

public interface TransactionStatusService {

    Transaction queryTransactionStatus(PostTransactionStatusRequest postTransactionStatusRequest);
}
