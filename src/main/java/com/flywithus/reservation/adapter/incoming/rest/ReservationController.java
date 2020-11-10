package com.flywithus.reservation.adapter.incoming.rest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.flywithus.infrastructure.security.SecurityContextFacade;
import com.flywithus.reservation.command.CancelReservationCommand;
import com.flywithus.reservation.command.ChangeReservationCommand;
import com.flywithus.reservation.command.MakeReservationCommand;
import com.flywithus.reservation.exception.CannotCancelReservationException;
import com.flywithus.reservation.exception.CannotChangeReservationException;
import com.flywithus.reservation.port.incoming.CancelReservationPort;
import com.flywithus.reservation.port.incoming.ChangeReservationPort;
import com.flywithus.reservation.port.incoming.MakeReservationPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
class ReservationController {

  private final MakeReservationPort makeReservationPort;
  private final ChangeReservationPort changeReservationPort;
  private final CancelReservationPort cancelReservationPort;
  private final SecurityContextFacade securityContextFacade;

  ReservationController(
      MakeReservationPort makeReservationPort,
      ChangeReservationPort changeReservationPort,
      CancelReservationPort cancelReservationPort,
      SecurityContextFacade securityContextFacade) {
    this.makeReservationPort = makeReservationPort;
    this.changeReservationPort = changeReservationPort;
    this.cancelReservationPort = cancelReservationPort;
    this.securityContextFacade = securityContextFacade;
  }

  @PostMapping
  void makeReservation(@RequestBody MakeReservationCommand command) {
    command.setUserId(securityContextFacade.findCurrentUserId());
    makeReservationPort.make(command);
  }

  @PutMapping
  void changeReservation(@RequestBody ChangeReservationCommand command) {
    changeReservationPort.change(command);
  }

  @DeleteMapping
  void cancelReservation(@RequestBody CancelReservationCommand command) {
    cancelReservationPort.cancel(command);
  }

  @ExceptionHandler(CannotChangeReservationException.class)
  ResponseEntity<Error> handleCannotChangeReservation(CannotChangeReservationException e) {
    Error error = new Error(e.getMessage());
    return new ResponseEntity<>(error, BAD_REQUEST);
  }

  @ExceptionHandler(CannotCancelReservationException.class)
  ResponseEntity<Error> handleCannotCancelReservation(CannotCancelReservationException e) {
    Error error = new Error(e.getMessage());
    return new ResponseEntity<>(error, BAD_REQUEST);
  }
}
