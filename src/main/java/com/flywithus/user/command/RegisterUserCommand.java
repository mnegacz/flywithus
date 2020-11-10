package com.flywithus.user.command;

public class RegisterUserCommand {

  private String username;
  private char[] password;

  public RegisterUserCommand() {}

  public RegisterUserCommand(String username, char[] password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public char[] getPassword() {
    return password;
  }

  public void setPassword(char[] password) {
    this.password = password;
  }
}
