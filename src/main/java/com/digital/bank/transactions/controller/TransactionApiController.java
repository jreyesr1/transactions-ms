package com.digital.bank.transactions.controller;

import com.digital.bank.transactions.api.TransactionApi;
import com.digital.bank.transactions.interfaces.transaction.TransactionService;
import com.digital.bank.transactions.model.CreateTransactionRequest;
import com.digital.bank.transactions.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.List;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-02T10:47:55.110553800-05:00[America/Guayaquil]")
@Controller
@RequestMapping("${openapi.transactionsMSDigitalBank.base-path:}")
public class TransactionApiController implements TransactionApi {

    private TransactionService transactionService;

    @Autowired
    public TransactionApiController(NativeWebRequest request, TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @Override
    public ResponseEntity<Transaction> createTransaction(CreateTransactionRequest createTransactionRequest
    ) {
       Transaction transaction= transactionService.createTransaction(createTransactionRequest);
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Transaction>> getTransaction(String accountIban,  String sortByAmount ) {
        return new ResponseEntity<>(transactionService.findTransactionByIban(accountIban,Optional.ofNullable(sortByAmount)),HttpStatus.OK);

    }


}
