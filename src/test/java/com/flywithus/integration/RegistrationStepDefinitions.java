package com.flywithus.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.flywithus.user.adapter.outgoing.InMemoryUserRepositoryAdapter;
import com.flywithus.user.command.RegisterUserCommand;
import com.flywithus.user.dto.UserDTO;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationStepDefinitions extends IntegrationTest {

  @Autowired private InMemoryUserRepositoryAdapter inMemoryUserRepositoryAdapter;

  private String username;
  private String password;

  @Before
  public void setUp() {
    username = null;
    password = null;
  }

  @After
  public void tearDown() {
    inMemoryUserRepositoryAdapter.clear();
  }

  @Given("username '(.*)' and password '(.*)' are provided")
  public void userNameAndPasswordAreProvided(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @When("the user registers")
  public void userRegisters() throws Exception {
    RegisterUserCommand command = new RegisterUserCommand(username, password.toCharArray());
    postJson("/user", command).andExpect(status().isOk());
  }

  @Then("the user is registered")
  public void userIsRegistered() {
    UserDTO user = inMemoryUserRepositoryAdapter.findByUsername(username);

    assertThat(user.getId()).isNotEmpty();
    assertThat(user.getUsername()).isEqualTo(username);
    assertThat(user.getPassword()).isEqualTo(password.toCharArray());
  }
}
