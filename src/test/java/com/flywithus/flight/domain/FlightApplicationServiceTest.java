package com.flywithus.flight.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.flywithus.flight.command.FindFlightCommand;
import com.flywithus.flight.command.SearchFlightCommand;
import com.flywithus.flight.dto.FindFlightDTO;
import com.flywithus.flight.dto.SearchFlightDTO;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FlightApplicationServiceTest {

  private static final FlightId FLIGHT_ID = FlightId.of("flight id");

  @Mock private FlightRepository flightRepository;

  @Mock private SearchFlightCommand command;

  @Mock private Flight flight;

  @Mock private FindFlightDTO findFlightDTO;

  @Mock private SearchFlightDTO searchFlightDTO;

  @InjectMocks private FlightApplicationService testee;

  @Test
  public void shouldSearchForFlights() {
    // given
    given(flightRepository.findAll(any())).willReturn(ImmutableList.of(flight));
    given(flight.toSearchFlightDTO()).willReturn(searchFlightDTO);

    // when
    List<SearchFlightDTO> result = testee.search(command);

    // then
    assertThat(result).containsExactly(searchFlightDTO);
  }

  @Test
  public void shouldSearchThrowIllegalArgumentExceptionWhenCommandIsNull() {
    // given
    SearchFlightCommand anotherCommand = null;

    // when
    Throwable result = catchThrowable(() -> testee.search(anotherCommand));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldFindFlight() {
    // given
    FindFlightCommand command = new FindFlightCommand(FLIGHT_ID.id());

    given(flightRepository.find(FLIGHT_ID)).willReturn(flight);
    given(flight.toFindFlightDTO()).willReturn(findFlightDTO);

    // when
    FindFlightDTO result = testee.find(command);

    // then
    assertThat(result).isEqualTo(findFlightDTO);
  }

  @Test
  public void shouldFindThrowIllegalArgumentExceptionWhenCommandIsNull() {
    // given
    FindFlightCommand anotherCommand = null;

    // when
    Throwable result = catchThrowable(() -> testee.find(anotherCommand));

    // then
    assertThat(result).isInstanceOf(IllegalArgumentException.class);
  }
}
