/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.digital.bank.transactions.api;

import com.digital.bank.transactions.model.CreateTransactionRequest;
import com.digital.bank.transactions.model.Transaction;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-02T18:16:14.627916400-05:00[America/Guayaquil]")
@Validated
@Tag(name = "Transaction", description = "the Transaction API")
public interface TransactionApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /transaction : Transaction
     * create transaction
     *
     * @param createTransactionRequest To create a Transaction the request body should contains at least &#39;account_iban&#39; and &#39;amount&#39; properties (optional)
     * @return Transaction created (status code 200)
     */
    @Operation(
        operationId = "createTransaction",
        summary = "Transaction",
        description = "create transaction",
        tags = { "Transaction" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Transaction created", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/transaction",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Transaction> createTransaction(
        @Parameter(name = "CreateTransactionRequest", description = "To create a Transaction the request body should contains at least 'account_iban' and 'amount' properties") @Valid @RequestBody(required = false) CreateTransactionRequest createTransactionRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"reference\" : \"reference\", \"date\" : \"2000-01-23T04:56:07.000+00:00\", \"amount\" : 0.8008281904610115, \"fee\" : 6.027456183070403, \"description\" : \"description\", \"id\" : 1, \"account\" : { \"account_iban\" : \"account_iban\", \"balance\" : 5.962133916683182, \"id\" : 5 }, \"status\" : \"PENDING\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /transaction : Transaction
     * Query transactions by iban
     *
     * @param accountIban account iban (required)
     * @param sortByAmount Sort by amount (ascending/descending) setting the values of the params (asc/desc) (optional)
     * @return It returns a list of transactions depending on the IBAN , if there&#39;s no transactions it will return an empty array (status code 200)
     */
    @Operation(
        operationId = "getTransaction",
        summary = "Transaction",
        description = "Query transactions by iban",
        tags = { "Transaction" },
        responses = {
            @ApiResponse(responseCode = "200", description = "It returns a list of transactions depending on the IBAN , if there's no transactions it will return an empty array", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Transaction.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/transaction",
        produces = { "application/json" }
    )
    default ResponseEntity<List<Transaction>> getTransaction(
        @NotNull @Parameter(name = "account_iban", description = "account iban", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "account_iban", required = true) String accountIban,
        @Parameter(name = "sort_by_amount", description = "Sort by amount (ascending/descending) setting the values of the params (asc/desc)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "sort_by_amount", required = false) String sortByAmount
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"reference\" : \"reference\", \"date\" : \"2000-01-23T04:56:07.000+00:00\", \"amount\" : 0.8008281904610115, \"fee\" : 6.027456183070403, \"description\" : \"description\", \"id\" : 1, \"account\" : { \"account_iban\" : \"account_iban\", \"balance\" : 5.962133916683182, \"id\" : 5 }, \"status\" : \"PENDING\" }, { \"reference\" : \"reference\", \"date\" : \"2000-01-23T04:56:07.000+00:00\", \"amount\" : 0.8008281904610115, \"fee\" : 6.027456183070403, \"description\" : \"description\", \"id\" : 1, \"account\" : { \"account_iban\" : \"account_iban\", \"balance\" : 5.962133916683182, \"id\" : 5 }, \"status\" : \"PENDING\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
