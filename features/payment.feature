Feature: Payment for reservations

  Scenario: Should payment for reservation be successful
    Given the flight '54ef8d68-6eff-11e7-907b-a6006ad3dba0' is provided
    And a reservation is made
    And a payment is registered
    When an operator confirms payment
    Then the payment is received

  Scenario: Should not received payments be expired two days after reservation
    Given the flight '54ef8d68-6eff-11e7-907b-a6006ad3dba0' is provided
    And a reservation '60e5d521-919d-49a7-89c5-b33a60a9302d' from 2 days is provided
    And a payment 'acf28291-3b3b-4823-be68-64e507597cd3' for the reservation is provided
    When the payments are checked
    Then the payment is expired
