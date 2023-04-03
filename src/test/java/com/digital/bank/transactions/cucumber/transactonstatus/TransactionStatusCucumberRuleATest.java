package com.digital.bank.transactions.cucumber.transactonstatus;

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
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionStatusCucumberRuleATest {

    @LocalServerPort
    String port;
    Transaction lastResponse;
    String url="/transaction/status";

    @Given("^a transaction that is not stored in our system$")
    public void a_transaction_that_is_not_stored_in_our_system() throws Exception {
        // No setup necessary
    }

    @When("^I check the status from any channel:$")
    public void i_check_the_status_from_any_channel(DataTable dataTable) throws Exception {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            PostTransactionStatusRequest.ChannelEnum channel = PostTransactionStatusRequest.ChannelEnum.valueOf(row.get("channel"));
            String reference = row.get("reference");
            PostTransactionStatusRequest postTransactionStatusRequest= PostTransactionStatusRequest.builder().reference(reference).channel(channel).build();
            lastResponse = new RestTemplate().postForObject("http://localhost:" + port + url,postTransactionStatusRequest , Transaction.class );
        }

    }


    @Then("^the system returns the status INVALID:$")
    public void the_system_returns_the_status_INVALID(DataTable expectedStatus) throws Exception {
        List<Map<String, String>> rows = expectedStatus.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            Assertions.assertTrue(lastResponse.getStatus().toString().equals(row.get("status") ));

        }
    }



}


