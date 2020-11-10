package com.flywithus.integration;

import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.flywithus.flight.adapter.outgoing.InMemoryFlightRepositoryAdapter;
import com.flywithus.flight.dto.FlightDTO;
import com.flywithus.payment.adapter.outgoing.InMemoryPaymentOperatorAdapter;
import com.flywithus.payment.adapter.outgoing.InMemoryPaymentRepositoryAdapter;
import com.flywithus.reservation.adapter.outgoing.InMemoryReservationRepositoryAdapter;
import com.flywithus.reservation.command.ChangeReservationCommand;
import com.flywithus.reservation.command.MakeReservationCommand;
import com.flywithus.reservation.dto.ClientDTO;
import com.flywithus.reservation.dto.ReservationDTO;
import com.flywithus.reservation.dto.ReservationStatusDTO;
import com.flywithus.user.adapter.outgoing.InMemoryUserRepositoryAdapter;
import com.flywithus.user.dto.UserDto;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationStepDefinitions extends IntegrationTest {

  private static final String CODE = "code";
  private static final String DEPARTURE_PLACE = "WRO";
  private static final LocalDateTime DEPARTURE_DATE_TIME =
      LocalDateTime.parse("2017-01-01T08:00:00");
  private static final String ARRIVAL_PLACE = "KRK";
  private static final LocalDateTime ARRIVAL_DATE_TIME = LocalDateTime.parse("2017-01-02T08:00:00");
  private static final int AVAILABLE_CAPACITY = 10;
  private static final BigDecimal PRICE = TEN;
  private static final int NUMBER_OF_PEOPLE = 1;
  private static final LocalDateTime NOW = LocalDateTime.now();
  private static final String USERNAME = "username";
  private static final String PASSWORD = "password";

  @Autowired private InMemoryReservationRepositoryAdapter inMemoryReservationRepositoryAdapter;

  @Autowired private InMemoryPaymentOperatorAdapter inMemoryPaymentOperatorAdapter;

  @Autowired private InMemoryFlightRepositoryAdapter inMemoryFlightRepositoryAdapter;

  @Autowired private InMemoryPaymentRepositoryAdapter inMemoryPaymentRepositoryAdapter;

  @Autowired private InMemoryUserRepositoryAdapter inMemoryUserRepositoryAdapter;

  private String flightId;
  private String anotherFlightId;
  private String reservationId;
  private String userId;

  @Before
  public void setUp() {
    flightId = null;
    reservationId = null;
    userId = null;
  }

  @After
  public void tearDown() {
    inMemoryReservationRepositoryAdapter.clear();
    inMemoryPaymentOperatorAdapter.clear();
    inMemoryFlightRepositoryAdapter.clear();
    inMemoryPaymentRepositoryAdapter.clear();
    inMemoryUserRepositoryAdapter.clear();
  }

  @Given("the flight '(.*)' with price (\\d+) is provided")
  public void flightWithPriceIsProvided(String flightId, int price) {
    this.flightId = flightId;

    FlightDTO dto =
        new FlightDTO(
            flightId,
            CODE,
            DEPARTURE_PLACE,
            DEPARTURE_DATE_TIME,
            ARRIVAL_PLACE,
            ARRIVAL_DATE_TIME,
            AVAILABLE_CAPACITY,
            BigDecimal.valueOf(price));
    inMemoryFlightRepositoryAdapter.save(dto);
  }

  @Given("the flight '(.*)' with departure date in (\\d+) days")
  public void flightWithDepartureDate(String flightId, int days) {
    this.flightId = flightId;

    LocalDateTime departureDateTime = NOW.plusDays(days);
    FlightDTO dto =
        new FlightDTO(
            flightId,
            CODE,
            DEPARTURE_PLACE,
            departureDateTime,
            ARRIVAL_PLACE,
            DEPARTURE_DATE_TIME,
            AVAILABLE_CAPACITY,
            PRICE);
    inMemoryFlightRepositoryAdapter.save(dto);
  }

  @Given("another flight '(.*)' is provided")
  public void anotherFlightWithPriceIsProvided(String anotherFlightId) {
    this.anotherFlightId = anotherFlightId;

    FlightDTO dto =
        new FlightDTO(
            anotherFlightId,
            CODE,
            DEPARTURE_PLACE,
            DEPARTURE_DATE_TIME,
            ARRIVAL_PLACE,
            ARRIVAL_DATE_TIME,
            AVAILABLE_CAPACITY,
            PRICE);
    inMemoryFlightRepositoryAdapter.save(dto);
  }

  @When("a client reserves the flight")
  public void clientReservesFlight() throws Exception {
    MakeReservationCommand command = new MakeReservationCommand(flightId, NUMBER_OF_PEOPLE);
    postJson("/reservation", command).andExpect(status().isOk());
    reservationId = inMemoryReservationRepositoryAdapter.only().getId();
  }

  @Then("the flight is reserved without discount")
  public void flightIsReservedWithoutDiscount() {
    ReservationDTO reservationDTO = inMemoryReservationRepositoryAdapter.find(reservationId);

    assertThat(reservationDTO.getFlight().getId()).isEqualTo(flightId);
    assertThat(reservationDTO.getDiscount()).isNull();
  }

  @And("a user is registered")
  public void userIsRegistered() {
    userId = uuid();
    UserDto userDTO =
        UserDto.builder().id(userId).username(USERNAME).password(PASSWORD.toCharArray()).build();
    inMemoryUserRepositoryAdapter.save(userDTO);
  }

  @And("the logged in client reserves the flight")
  public void loggedInClientReservesFlight() throws Exception {
    MakeReservationCommand command = new MakeReservationCommand(flightId, NUMBER_OF_PEOPLE);
    sendJson(post("/reservation").with(httpBasic(USERNAME, PASSWORD)), command)
        .andExpect(status().isOk());
    reservationId = inMemoryReservationRepositoryAdapter.only().getId();
  }

  @Then("the flight is reserved with (\\d+)% discount")
  public void flightIsReservedWithDiscount(int percent) {
    ReservationDTO reservationDTO = inMemoryReservationRepositoryAdapter.find(reservationId);

    assertThat(reservationDTO.getFlight().getId()).isEqualTo(flightId);
    assertThat(reservationDTO.getDiscount()).isNotNull();
    assertThat(reservationDTO.getDiscount().getPercent()).isEqualTo(BigDecimal.valueOf(percent));
  }

  @And("the reservation '(.*)' is provided")
  public void reservationIsProvided(String reservationId) {
    this.reservationId = reservationId;

    ClientDTO clientDTO = new ClientDTO();
    com.flywithus.reservation.dto.FlightDTO flightDTO =
        new com.flywithus.reservation.dto.FlightDTO(flightId, DEPARTURE_DATE_TIME, PRICE);

    ReservationDTO reservationDTO =
        new ReservationDTO(
            reservationId,
            clientDTO,
            flightDTO,
            NUMBER_OF_PEOPLE,
            NOW,
            ReservationStatusDTO.CREATED);
    inMemoryReservationRepositoryAdapter.save(reservationDTO);
  }

  @When("a client changes a reservation")
  public void clientChangesReservation() throws Exception {
    ChangeReservationCommand command =
        new ChangeReservationCommand(reservationId, anotherFlightId, NUMBER_OF_PEOPLE);
    putJson("/reservation", command);
  }

  @Then("the reservation is changed")
  public void reservationIsChanged() {
    ReservationDTO reservationDTO = inMemoryReservationRepositoryAdapter.find(reservationId);

    assertThat(reservationDTO.getFlight().getId()).isEqualTo(anotherFlightId);
  }

  @Then("the reservation is not changed")
  public void reservationIsNotChanged() {
    ReservationDTO reservationDTO = inMemoryReservationRepositoryAdapter.find(reservationId);

    assertThat(reservationDTO.getFlight().getId()).isEqualTo(flightId);
  }

  @When("a client cancels a reservation")
  public void clientCancelsReservation() throws Exception {
    ChangeReservationCommand command =
        new ChangeReservationCommand(reservationId, anotherFlightId, 1);
    deleteJson("/reservation", command);
  }

  @Then("the reservation is canceled")
  public void reservationIsCancelled() {
    ReservationDTO reservationDTO = inMemoryReservationRepositoryAdapter.find(reservationId);

    assertThat(reservationDTO.getStatus()).isEqualTo(ReservationStatusDTO.CANCELLED);
  }

  @Then("the reservation is not canceled")
  public void reservationIsNotCancelled() {
    ReservationDTO reservationDTO = inMemoryReservationRepositoryAdapter.find(reservationId);

    assertThat(reservationDTO.getStatus()).isNotEqualTo(ReservationStatusDTO.CANCELLED);
  }
}
