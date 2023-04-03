package com.digital.bank.transactions.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@ActiveProfiles("test")
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty"},
        glue = {"com.digital.bank.transactions.cucumber"})
public class CucumberTestRunner {
}