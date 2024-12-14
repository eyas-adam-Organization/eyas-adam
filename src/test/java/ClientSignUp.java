import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;


public class ClientSignUp {
    Clients clients;

    public ClientSignUp(Clients c){
        clients = c;
    }


    String actualMessage;

    @When("client entered username {string} and password {string}")
    public void client_entered_username_and_password(String username, String password){
        actualMessage = clients.clientSignUp(username, password);
    }

    @Then("tell client that the username is used")
    public void tell_client_that_the_username_is_used() {
        assertEquals(Clients.failedUsernameSignupMessage, actualMessage);
    }

    @Then("tell the used that the password is short")
    public void tell_the_used_that_the_password_is_short() {
        assertEquals(Clients.failedPasswordSignupMessage, actualMessage);
    }

    @Then("tell the user that the account was created successfully")
    public void tell_the_user_that_the_account_was_created_successfully_for_user() {
        assertEquals(Clients.successSignupMessage, actualMessage);
    }

    @Then("sign the user in with username {string}")
    public void sign_the_user_in_with_username(String username) {
        assertEquals(username, clients.activeUser);
        assertEquals(Clients.clientProfileCode, clients.currentMenu);
    }

    @Then("redirect client to sign up")
    public void redirect_client_to_sign_up() {
        assertEquals(Clients.clientSignupCode, clients.currentMenu);
    }
}
