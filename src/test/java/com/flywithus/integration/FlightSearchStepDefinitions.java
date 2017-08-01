package com.flywithus.integration;

import com.flywithus.flight.adapter.outgoing.InMemoryFlightRepositoryAdapter;
import com.flywithus.flight.command.SearchFlightCommand;
import com.flywithus.flight.dto.FlightDTO;
import com.flywithus.flight.dto.SearchFlightDTO;
import com.google.gson.reflect.TypeToken;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FlightSearchStepDefinitions extends IntegrationTest {

    private static final String ID = "FLIGHT-1";
    private static final String CODE = "code";
    private static final String DEPARTURE_PLACE = "WRO";
    private static final LocalDateTime DEPARTURE_DATE_TIME = LocalDateTime.parse("2017-01-01T08:00:00");
    private static final String ARRIVAL_PLACE = "KRK";
    private static final LocalDateTime ARRIVAL_DATE_TIME = LocalDateTime.parse("2017-01-02T08:00:00");
    private static final int AVAILABLE_CAPACITY = 10;
    private static final BigDecimal PRICE = TEN;

    @Autowired
    private InMemoryFlightRepositoryAdapter inMemoryFlightRepositoryAdapter;

    private SearchFlightCommand command;
    private List<SearchFlightDTO> flights;

    @Before
    public void setUp() {
        command = new SearchFlightCommand();
    }

    @After
    public void tearDown() {
        inMemoryFlightRepositoryAdapter.clear();
    }

    @Given("a flight is provided")
    public void flightIsProvided() {
        FlightDTO dto = new FlightDTO(ID, CODE, DEPARTURE_PLACE, DEPARTURE_DATE_TIME, ARRIVAL_PLACE, ARRIVAL_DATE_TIME, AVAILABLE_CAPACITY, PRICE);
        inMemoryFlightRepositoryAdapter.save(dto);
    }

    @When("a client search for flights")
    public void clientSearchFlights() throws Exception {
        MvcResult result = postJson("/flight", command)
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        flights = gson().fromJson(content, flightCollectionType());
    }

    private Type flightCollectionType() {
        return new FlightListTypeToken().getType();
    }

    @Then("the flight '(.*)' is shown")
    public void reservationIsPaid(String flightId) {
        assertThat(flights).extracting("id")
                .contains(flightId);
    }

    @And("the departure place is '(.*)'")
    public void departurePlace(String place) {
        command.setDeparturePlace(place);
    }

    @And("the arrival place is '(.*)'")
    public void arrivalPlace(String place) {
        command.setArrivalPlace(place);
    }

    @And("the departure date is '(.*)'")
    public void departureDate(String date) {
        command.setDepartureDate(LocalDate.parse(date));
    }

    @And("the arrival date is '(.*)'")
    public void arrivalDate(String date) {
        command.setArrivalDate(LocalDate.parse(date));
    }

    @And("the number of people is (\\d)")
    public void numberOfPeople(int numberOfPeople) {
        command.setNumberOfPeople(numberOfPeople);
    }

    private static class FlightListTypeToken extends TypeToken<List<SearchFlightDTO>> {
    }

}
