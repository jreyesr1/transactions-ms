
package com.digital.bank.transactions.cucumber.transactonstatus;

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
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionStatusCucumberRuleBTest {

    @LocalServerPort
    String port;
    Transaction lastResponse;
    String url = "/transaction/status";
    String transactionEndPoint = "/transaction";

    List<Transaction> responses = new ArrayList<>();


    @Given("^a transaction that is stored in our system$")
    public void a_transaction_that_is_stored_in_our_system() throws Exception {

    }

    @When("^I check the status from CLIENT or ATM channel:$")
    public void I_check_the_status_from_CLIENT_or_ATM_channel(DataTable dataTable) throws Exception {
        postTransactionsWithDayBefore();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            PostTransactionStatusRequest.ChannelEnum channel = PostTransactionStatusRequest.ChannelEnum.valueOf(row.get("channel"));
            String reference = row.get("reference");
            PostTransactionStatusRequest postTransactionStatusRequest = PostTransactionStatusRequest.builder().reference(reference).channel(channel).build();
            lastResponse = new RestTemplate().postForObject("http://localhost:" + port + url, postTransactionStatusRequest, Transaction.class);
            System.out.printf("response " + lastResponse);
            responses.add(lastResponse);
        }

    }


    @Then("^the system returns the status SETTLED And the amount substracting the fee:$")
    public void the_system_returns_the_status_settled_and_the_amount_substracting_the_fee(DataTable expectedStatus) throws Exception {
        List<Map<String, String>> rows = expectedStatus.asMaps(String.class, String.class);
        int index = 0;
        for (Map<String, String> row : rows) {
            Transaction response = responses.get(index);
            String status = response.getStatus().toString();
            String amount = response.getAmount().toString();
            String reference = response.getReference();
            System.out.println("se compara ");
            System.out.println(status +":"+row.get("status"));
            System.out.println(amount +":"+row.get("amount"));
            System.out.println(reference +":"+row.get("reference"));
            Assertions.assertEquals(status,row.get("status"));
            Assertions.assertEquals(amount,row.get("amount"));
            Assertions.assertEquals(reference,row.get("reference"));
            index++;


        }


    }

    void postTransactionsWithDayBefore() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String dayBefore = OffsetDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

        CreateTransactionRequest createTransactionRequest1 = CreateTransactionRequest.builder()
                .amount(BigDecimal.valueOf(205))
                .fee(BigDecimal.valueOf(5))
                .date(OffsetDateTime.parse(dayBefore))
                .reference("abcdefg")
                .description("Restaurant payment")
                .accountIban("ES56321478963214523658974")
                .build();

        dayBefore = OffsetDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        CreateTransactionRequest createTransactionRequest2 = CreateTransactionRequest.builder()
                .amount(BigDecimal.valueOf(302))
                .fee(BigDecimal.valueOf(2))
                .date(OffsetDateTime.parse(dayBefore))
                .reference("hijklmn")
                .description("Restaurant payment")
                .accountIban("ES56321478963214523658974")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateTransactionRequest> request1 = new HttpEntity<>(createTransactionRequest1, headers);
        HttpEntity<CreateTransactionRequest> request2 = new HttpEntity<>(createTransactionRequest2, headers);


        ResponseEntity<Transaction> response = restTemplate.exchange("http://localhost:" + port + transactionEndPoint,
                HttpMethod.POST, request1, Transaction.class);
        response = restTemplate.exchange("http://localhost:" + port + transactionEndPoint,
                HttpMethod.POST, request2, Transaction.class);


    }
}


