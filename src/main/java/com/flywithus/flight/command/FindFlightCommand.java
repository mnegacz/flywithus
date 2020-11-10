package com.flywithus.flight.command;

public class FindFlightCommand {

  private String id;

  public FindFlightCommand() {}

  public FindFlightCommand(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
