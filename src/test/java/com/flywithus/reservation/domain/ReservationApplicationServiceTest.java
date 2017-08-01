package com.flywithus.reservation.domain;

import com.flywithus.reservation.command.CancelReservationCommand;
import com.flywithus.reservation.command.ChangeReservationCommand;
import com.flywithus.reservation.command.FindReservationCommand;
import com.flywithus.reservation.command.MakeReservationCommand;
import com.flywithus.reservation.dto.FindReservationDTO;
import com.flywithus.reservation.event.ReservationMadeEvent;
import com.flywithus.reservation.port.outgoing.EventPublisherPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReservationApplicationServiceTest {

    private static final Optional<UserId> NO_CLIENT_ID = Optional.empty();
    private static final ReservationId RESERVATION_ID = ReservationId.of("reservation id");
    private static final FlightId FLIGHT_ID = FlightId.of("flight id");
    private static final NumberOfPeople NUMBER_OF_PEOPLE = NumberOfPeople.of(3);

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private ReservationFactory reservationFactory;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private DateTimeFactory dateTimeFactory;

    @Mock
    private EventPublisherPort eventPublisherPort;

    @Mock
    private Flight flight;

    @Mock
    private Client unregisteredClient;

    @Mock
    private Reservation reservation;

    @Mock
    private DateTime now;

    @Mock
    private FindReservationDTO findReservationDTO;

    @Captor
    private ArgumentCaptor<Object> eventCaptor;

    @InjectMocks
    private ReservationApplicationService testee;

    @Before
    public void setUp() {
        given(flightRepository.find(FLIGHT_ID)).willReturn(flight);
        given(reservationRepository.find(RESERVATION_ID)).willReturn(reservation);
        given(clientRepository.find(NO_CLIENT_ID)).willReturn(unregisteredClient);
        given(dateTimeFactory.now()).willReturn(now);
        given(reservation.id()).willReturn(RESERVATION_ID);
        given(reservation.flight()).willReturn(flight);
        given(flight.id()).willReturn(FLIGHT_ID);
    }

    @Test
    public void shouldMakeReservation() {
        // given
        MakeReservationCommand command = new MakeReservationCommand(FLIGHT_ID.id(), NUMBER_OF_PEOPLE.number());

        given(reservationFactory.createReservation(unregisteredClient, flight, NUMBER_OF_PEOPLE)).willReturn(reservation);
        given(reservation.id()).willReturn(RESERVATION_ID);

        // when
        testee.make(command);

        // then
        verify(reservationRepository).save(reservation);
    }

    @Test
    public void shouldMakePublishReservationMadeEvent() {
        // given
        MakeReservationCommand command = new MakeReservationCommand(FLIGHT_ID.id(), NUMBER_OF_PEOPLE.number());

        given(reservationFactory.createReservation(unregisteredClient, flight, NUMBER_OF_PEOPLE)).willReturn(reservation);
        given(reservation.id()).willReturn(RESERVATION_ID);

        // when
        testee.make(command);

        // then
        verify(eventPublisherPort).publishEvent(eventCaptor.capture());

        Object event = eventCaptor.getValue();
        assertThat(event).isInstanceOf(ReservationMadeEvent.class);

        ReservationMadeEvent reservationMadeEvent = ReservationMadeEvent.class.cast(event);
        assertThat(reservationMadeEvent.getId()).isNotEmpty();
    }

    @Test
    public void shouldMakeReservationThrowIllegalArgumentExceptionWhenCommandIsNull() {
        // given
        MakeReservationCommand command = null;

        // when
        Throwable result = catchThrowable(() -> testee.make(command));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldChangeTheReservation() {
        // given
        ChangeReservationCommand command = new ChangeReservationCommand(RESERVATION_ID.id(), FLIGHT_ID.id(), NUMBER_OF_PEOPLE.number());

        // when
        testee.change(command);

        // then
        reservation.change(flight, NUMBER_OF_PEOPLE, now);
    }

    @Test
    public void shouldChangeReservationThrowIllegalArgumentExceptionWhenCommandIsNull() {
        // given
        ChangeReservationCommand command = null;

        // when
        Throwable result = catchThrowable(() -> testee.change(command));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldCancelTheReservation() {
        // given
        CancelReservationCommand command = new CancelReservationCommand(RESERVATION_ID.id());

        // when
        testee.cancel(command);

        // then
        verify(reservation).cancel(now);
    }

    @Test
    public void shouldCancelReservationThrowIllegalArgumentExceptionWhenCommandIsNull() {
        // given
        CancelReservationCommand command = null;

        // when
        Throwable result = catchThrowable(() -> testee.cancel(command));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldFindReservation() {
        // given
        FindReservationCommand command = new FindReservationCommand(RESERVATION_ID.id());

        given(reservationRepository.find(RESERVATION_ID)).willReturn(reservation);
        given(reservation.toFindReservationDTO()).willReturn(findReservationDTO);

        // when
        FindReservationDTO result = testee.find(command);

        // then
        assertThat(result).isEqualTo(findReservationDTO);
    }

    @Test
    public void shouldFindThrowIllegalArgumentExceptionWhenCommandIsNull() {
        // given
        FindReservationCommand anotherCommand = null;

        // when
        Throwable result = catchThrowable(() -> testee.find(anotherCommand));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

}
