Feature: Transaction Service
  The user should be able to create transactions with an existing Account in our database, all those
  operations are made with the IBAN code of the Account.


  Scenario: Creating a Transaction sending the IBAN and the amount
    Given an account with balance of 1000 that is stored in our database
    When I create the Transaction, the amount will be accredited or deducted from the balance of the account:
      | IBAN  | amount |
      | ES56321478963214523658974   | 100   |
    Then the system returns the account with the new balance:
      | IBAN   | balance |
      | ES56321478963214523658974  | 1100   |



