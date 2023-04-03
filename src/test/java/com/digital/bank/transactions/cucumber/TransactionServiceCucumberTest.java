
package com.digital.bank.transactions.cucumber;

import com.digital.bank.transactions.model.CreateTransactionRequest;
import com.digital.bank.transactions.model.PostTransactionStatusRequest;
import com.digital.bank.transactions.model.Transaction;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class TransactionServiceCucumberTest {

    @LocalServerPort
    String port;
    Transaction lastResponse;
    String transactionEndPoint = "/transaction";

    List<Transaction> responses = new ArrayList<>();


    @Given("^an account with balance of 1000 that is stored in our database$")
    public void an_account_with_balance_of_that_is_stored_in_our_database() throws Exception {

    }

    @When("^I create the Transaction, the amount will be accredited or deducted from the balance of the account:$")
    public void i_create_the_transaction_the_amount_will_be_accredited_or_deducted_from_the_balance_of_the_account(DataTable dataTable) throws Exception {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            CreateTransactionRequest createTransactionRequest1 = CreateTransactionRequest.builder()
                    .amount(new BigDecimal(row.get("amount")))
                    .accountIban(row.get("IBAN"))
                    .build();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<CreateTransactionRequest> request1 = new HttpEntity<>(createTransactionRequest1, headers);

            ResponseEntity<Transaction> response = restTemplate.exchange("http://localhost:" + port + transactionEndPoint,
                    HttpMethod.POST, request1, Transaction.class);

            lastResponse=response.getBody();

        }

    }


    @Then("^the system returns the account with the new balance:$")
    public void the_system_returns_the_account_with_the_new_balance(DataTable expectedStatus) throws Exception {
        List<Map<String, String>> rows = expectedStatus.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            Transaction response = lastResponse;
            String IBAN = response.getAccount().getAccountIban();
            String balance = response.getAccount().getBalance().toString();

            Assertions.assertEquals(IBAN,row.get("IBAN"));
            Assertions.assertEquals(balance,row.get("balance"));
        }


    }
}


