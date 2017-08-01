Feature: Registration of new users

  Scenario: Should register a new user
    Given username 'john' and password 'secret' are provided
    When the user registers
    Then the user is registered
