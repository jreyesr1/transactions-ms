Feature: Query Transaction Status
  The user should be able to check the Status of the Transaction.


  Scenario: Get transaction that is not stored
    Given a transaction that is not stored in our system
    When I check the status from any channel:
      | channel  | reference |
      | CLIENT   | foo-bar   |
      | ATM      | foo-bar   |
      | INTERNAL | foo-bar   |
    Then the system returns the status INVALID:
      | status   | reference |
      | INVALID  | foo-bar   |


  Scenario: Getting Status of Transaction sending "CLIENT" or "ATM" channel, and the transaction date is before today
    Given a transaction that is stored in our system
    When I check the status from CLIENT or ATM channel:
      | channel  | reference |
      | CLIENT   | abcdefg   |
      | ATM      | hijklmn   |
    Then the system returns the status SETTLED And the amount substracting the fee:
      | status   | reference | amount  |
      | SETTLED  | abcdefg   | 200     |
      | SETTLED  | hijklmn   | 300     |

  Scenario: Getting Status of Transaction sending "INTERNAL" channel, and the transaction date is before today
    Given a transaction that is stored in our System
    When I check the status from INTERNAL channel And the transaction date is before today:
      | channel  | reference |
      | INTERNAL | 1234ABC   |
    Then The system returns the status 'SETTLED' And the amount And the fee:
      | status   | amount    | fee   |
      | SETTLED  | 150       | 5   |



