Feature: Reservation of flights

  Scenario: Should reserve a flight
    Given the flight '4b81b992-42c4-4e88-8889-9cf53ba93fd4' with price 100 is provided
    When a client reserves the flight
    Then the flight is reserved without discount

  Scenario: Should reserve a flight with a discount for the registered user
    Given the flight '4b81b992-42c4-4e88-8889-9cf53ba93fd4' with price 100 is provided
    And a user is registered
    When the logged in client reserves the flight
    Then the flight is reserved with 5% discount

  Scenario: Should change a reservation
    Given the flight '4b81b992-42c4-4e88-8889-9cf53ba93fd4' with departure date in 5 days
    And the reservation '39bc5edf-09e0-440b-bbad-4fd0ffa6cfb1' is provided
    And another flight '3effa34a-fe9b-4117-8bcd-3905bf066283' is provided
    When a client changes a reservation
    Then the reservation is changed

  Scenario: Should not change a reservation later than 3 days before the departure date
    Given the flight '4b81b992-42c4-4e88-8889-9cf53ba93fd4' with departure date in 3 days
    And the reservation '39bc5edf-09e0-440b-bbad-4fd0ffa6cfb1' is provided
    And another flight '3effa34a-fe9b-4117-8bcd-3905bf066283' is provided
    When a client changes a reservation
    Then the reservation is not changed

  Scenario: Should cancel a reservation
    Given the flight '4b81b992-42c4-4e88-8889-9cf53ba93fd4' with departure date in 7 days
    And the reservation '39bc5edf-09e0-440b-bbad-4fd0ffa6cfb1' is provided
    When a client cancels a reservation
    Then the reservation is canceled

  Scenario: Should not cancel a reservation later than 5 days before the departure date
    Given the flight '4b81b992-42c4-4e88-8889-9cf53ba93fd4' with departure date in 5 days
    And the reservation '39bc5edf-09e0-440b-bbad-4fd0ffa6cfb1' is provided
    When a client cancels a reservation
    Then the reservation is not canceled
