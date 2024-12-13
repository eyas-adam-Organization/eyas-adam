import io.cucumber.java.en.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class ClientSignUp {
    Clients clients;

    public ClientSignUp(Clients c){
        clients = c;
    }


    String actualMessage;

    @When("client entered username {string} and password {string}")
    public void client_entered_username_and_password(String username, String password) throws FileNotFoundException {
        actualMessage = clients.clientSignUp(username, password);
    }

    @Then("tell client that the username is used")
    public void tell_client_that_the_username_is_used() {
        assertEquals("LOGIN UNSUCCESSFUL: Username already used, try another unique username", actualMessage);
    }

    @Then("tell the used that the password is short")
    public void tell_the_used_that_the_password_is_short() {
        assertEquals("LOGIN UNSUCCESSFUL: Password should be at least 8 characters long", actualMessage);
    }

    @Then("tell the user that the account was created successfully for user {string}")
    public void tell_the_user_that_the_account_was_created_successfully_for_user(String username) {
        assertEquals("LOGIN UNSUCCESSFUL: Welcome " + username, actualMessage);
    }

    @Then("sign the user in with username {string}")
    public void sign_the_user_in_with_username(String username) {
        assertEquals(username, clients.getActiveUser());
    }

}
