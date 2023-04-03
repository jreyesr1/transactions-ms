package com.digital.bank.transactions.controller;

import com.digital.bank.transactions.api.ApiUtil;
import com.digital.bank.transactions.api.StatusApi;
import com.digital.bank.transactions.interfaces.transaction.TransactionStatusService;
import com.digital.bank.transactions.model.PostTransactionStatusRequest;
import com.digital.bank.transactions.model.Transaction;


import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-02T10:47:55.110553800-05:00[America/Guayaquil]")
@Controller
@RequestMapping("${openapi.transactionsMSDigitalBank.base-path:}")
public class StatusApiController implements StatusApi {

    private TransactionStatusService transactionStatusService;

    @Autowired
    public StatusApiController(TransactionStatusService transactionStatusService) {
        this.transactionStatusService = transactionStatusService;
    }


    @Override
    public ResponseEntity<Transaction> postTransactionStatus(PostTransactionStatusRequest postTransactionStatusRequest) {
        Transaction transaction = transactionStatusService.queryTransactionStatus(postTransactionStatusRequest);
        return new ResponseEntity<>(transaction,HttpStatus.OK);

    }

}
