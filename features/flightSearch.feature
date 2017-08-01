Feature: Searching of flights

  Scenario: Should show full flight list
    Given a flight is provided
    When a client search for flights
    Then the flight 'FLIGHT-1' is shown

  Scenario: Should narrow flight list by a departure place
    Given a flight is provided
    And the departure place is 'WRO'
    When a client search for flights
    Then the flight 'FLIGHT-1' is shown

  Scenario: Should narrow flight list by an arrival place
    Given a flight is provided
    And the arrival place is 'KRK'
    When a client search for flights
    Then the flight 'FLIGHT-1' is shown

  Scenario: Should narrow flight list by a departure date
    Given a flight is provided
    And the departure date is '2017-01-01'
    When a client search for flights
    Then the flight 'FLIGHT-1' is shown

  Scenario: Should narrow flight list by an arrival date
    Given a flight is provided
    And the arrival date is '2017-01-02'
    When a client search for flights
    Then the flight 'FLIGHT-1' is shown

  Scenario: Should narrow flight list by a number of people
    Given a flight is provided
    And the number of people is 2
    When a client search for flights
    Then the flight 'FLIGHT-1' is shown

  Scenario: Should narrow flight list by combined criteria
    Given a flight is provided
    And the departure place is 'WRO'
    And the arrival place is 'KRK'
    And the departure date is '2017-01-01'
    And the arrival date is '2017-01-02'
    And the number of people is 2
    When a client search for flights
    Then the flight 'FLIGHT-1' is shown
