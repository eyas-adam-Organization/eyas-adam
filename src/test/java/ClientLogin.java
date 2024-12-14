import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientLogin {
    Clients clients;
    String actualResponseMessage;
    public ClientLogin(Clients c){
        clients = c;
    }

    @When("Client tries to login using username {string}  and password {string}")
    public void client_tries_to_login_using_username_and_password(String username, String password) {
        actualResponseMessage = clients.clientLogin(username, password);
    }

    @Then("Tell the client that they were logged in successfully")
    public void tell_the_client_that_they_were_logged_in_successfully() {
        assertEquals(Clients.successLoginMessage, actualResponseMessage);
    }

    @Then("set the current registered user {string}")
    public void set_the_current_registered_user(String username) {
        assertEquals(username, clients.activeUser);
    }

    @Then("redirect client to user profile")
    public void redirect_client_to_user_profile() {
        assertEquals(Clients.clientProfileCode, clients.currentMenu);
    }

    @Then("Tell the client that they were not logged in successfully due to username issue")
    public void tell_the_client_that_they_were_not_logged_in_successfully_due_to_username_issue() {
        assertEquals(Clients.failedUsernameLoginMessage, actualResponseMessage);
    }

    @Then("Tell the client that they were not logged in successfully due to password issue")
    public void tell_the_client_that_they_were_not_logged_in_successfully_due_to_password_issue() {
        assertEquals(Clients.failedPasswordLoginMessage, actualResponseMessage);
    }

    @Then("redirect client back to login")
    public void redirect_client_back_to_login() {
        assertEquals(Clients.clientLoginCode, clients.currentMenu);
    }
}
