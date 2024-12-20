import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;


public class ClientSignUp {
    Clients clients;
    Program program;

    public ClientSignUp(Clients c){
        clients = c;
    }


    String actualMessage;

    @When("client entered username {string} and password {string} for program {string}")
    public void client_entered_username_and_password(String username, String password, String program){
        actualMessage = clients.clientSignUp(username, password, program);
    }

    @Then("tell client that the username is used")
    public void tell_client_that_the_username_is_used() {
        assertEquals(Clients.FAILED_SIGNUP_USERNAME_MESSAGE, actualMessage);
    }

    @Then("tell the used that the password is short")
    public void tell_the_used_that_the_password_is_short() {
        assertEquals(Clients.FAILED_SIGNUP_PASSWORD_MESSAGE, actualMessage);
    }

    @Then("tell client that the program is not a registered program")
    public void tellClientThatTheProgramIsNotARegisteredProgram() {
        assertEquals(Clients.FAILED_SIGNUP_PROGRAM_MESSAGE, actualMessage);
    }

    @Then("tell the user that the account was created successfully")
    public void tell_the_user_that_the_account_was_created_successfully_for_user() {
        assertEquals(Clients.SUCCESS_SIGNUP_MESSAGE, actualMessage);
    }

    @Then("sign the user in with username {string}")
    public void sign_the_user_in_with_username(String username) {
        assertEquals(username, clients.activeClient);
        assertEquals(Clients.CLIENT_PROFILE_CODE, clients.currentMenu);
    }

    @Then("redirect client to sign up")
    public void redirect_client_to_sign_up() {
        assertEquals(Clients.CLIENT_SIGNUP_CODE, clients.currentMenu);
    }

    @Then("add the user {string} to the list of clients registered in the program {string}")
    public void addTheUserToTheListOfClientsRegisteredInTheProgram(String username, String program) {
        assertTrue(Clients.isClientInProgram(username, program));
        Clients.removeClientFromProgram(username, program);
    }
}
