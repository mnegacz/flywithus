package com.flywithus.integration;

import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.flywithus.flight.adapter.outgoing.InMemoryFlightRepositoryAdapter;
import com.flywithus.flight.dto.FlightDTO;
import com.flywithus.payment.adapter.incoming.task.ExpirePaymentTask;
import com.flywithus.payment.adapter.outgoing.InMemoryPaymentOperatorAdapter;
import com.flywithus.payment.adapter.outgoing.InMemoryPaymentRepositoryAdapter;
import com.flywithus.payment.command.ReceivePaymentCommand;
import com.flywithus.payment.dto.PaymentDTO;
import com.flywithus.payment.dto.PaymentStatusDTO;
import com.flywithus.reservation.adapter.outgoing.InMemoryReservationRepositoryAdapter;
import com.flywithus.reservation.command.MakeReservationCommand;
import com.flywithus.reservation.dto.ClientDTO;
import com.flywithus.reservation.dto.ReservationDTO;
import com.flywithus.reservation.dto.ReservationStatusDTO;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentStepDefinitons extends IntegrationTest {

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

  @Autowired private InMemoryReservationRepositoryAdapter inMemoryReservationRepositoryAdapter;

  @Autowired private InMemoryPaymentOperatorAdapter inMemoryPaymentOperatorAdapter;

  @Autowired private InMemoryFlightRepositoryAdapter inMemoryFlightRepositoryAdapter;

  @Autowired private InMemoryPaymentRepositoryAdapter inMemoryPaymentRepositoryAdapter;

  @Autowired private ExpirePaymentTask expirePaymentTask;

  private String flightId;
  private String reservationId;
  private String paymentId;

  @Before
  public void setUp() {
    flightId = null;
    reservationId = null;
    paymentId = null;
  }

  @After
  public void tearDown() {
    inMemoryReservationRepositoryAdapter.clear();
    inMemoryPaymentOperatorAdapter.clear();
    inMemoryFlightRepositoryAdapter.clear();
    inMemoryPaymentRepositoryAdapter.clear();
  }

  @Given("the flight '(.*)' is provided")
  public void flightIsProvided(String flightId) {
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
            PRICE);
    inMemoryFlightRepositoryAdapter.save(dto);
  }

  @Given("a reservation is made")
  public void reservationIsMade() throws Exception {
    MakeReservationCommand command = new MakeReservationCommand(flightId, NUMBER_OF_PEOPLE);
    postJson("/reservation", command).andExpect(status().isOk());
    reservationId = inMemoryReservationRepositoryAdapter.only().getId();
  }

  @And("a payment is registered")
  public void paymentIsRegistered() {
    paymentId = inMemoryPaymentOperatorAdapter.only();
  }

  @When("an operator confirms payment")
  public void operatorConfirmsPayment() throws Exception {
    ReceivePaymentCommand command = new ReceivePaymentCommand(paymentId);
    putJson("/payment", command).andExpect(status().isOk());
  }

  @Then("the payment is received")
  public void reservationIsPaid() {
    PaymentDTO paymentDTO = inMemoryPaymentRepositoryAdapter.find(paymentId);

    assertThat(paymentDTO.getStatus()).isEqualTo(PaymentStatusDTO.RECEIVED);
  }

  @And("a reservation '(.*)' from 2 days is provided")
  public void reservationFromTwoDaysIsProvided(String reservationId) {
    this.reservationId = reservationId;

    LocalDateTime twoDaysAgo = NOW.minusDays(2);
    ClientDTO clientDTO = new ClientDTO();
    com.flywithus.reservation.dto.FlightDTO flightDTO =
        new com.flywithus.reservation.dto.FlightDTO(flightId, DEPARTURE_DATE_TIME, TEN);

    ReservationDTO reservationDTO =
        new ReservationDTO(
            reservationId, clientDTO, flightDTO, 1, twoDaysAgo, ReservationStatusDTO.CREATED);
    inMemoryReservationRepositoryAdapter.save(reservationDTO);
  }

  @And("a payment '(.*)' for the reservation is provided")
  public void paymentForReservationIsProvided(String paymentId) {
    this.paymentId = paymentId;

    com.flywithus.payment.dto.ReservationDTO reservationDTO =
        new com.flywithus.payment.dto.ReservationDTO(reservationId, PRICE, NOW);
    PaymentDTO paymentDTO = new PaymentDTO(paymentId, PaymentStatusDTO.CREATED, reservationDTO);
    inMemoryPaymentRepositoryAdapter.save(paymentDTO);
  }

  @When("the payments are checked")
  public void paymentsAreChecked() throws Exception {
    expirePaymentTask.expire();
  }

  @Then("the payment is expired")
  public void paymentIsExpired() {
    PaymentDTO paymentDTO = inMemoryPaymentRepositoryAdapter.find(paymentId);

    assertThat(paymentDTO.getStatus()).isEqualTo(PaymentStatusDTO.EXPIRED);
  }
}
