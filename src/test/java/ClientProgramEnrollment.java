import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientProgramEnrollment {
    Clients clients;
    Client client;
    String username;
    String program;


    public ClientProgramEnrollment(Clients c){
        this.clients = c;
    }

    String actualResponse;


    @When("A registered client {string} tries to enroll in a program {string}")
    public void a_registered_client_tries_to_enroll_in_a_program(String username, String program) {
        client = clients.getClientByUsername(username);
        actualResponse = client.enrollInProgram(program);
        this.username = username;
        this.program = program;
    }

    @Then("tell client that this program is not a registered program")
    public void tell_client_that_this_program_is_not_a_registered_program() {
        assertEquals(Client.ENROLLMENT_REPLY_MESSAGES[Client.ENROLLMENT_FAIL_PROGRAM_DOES_NOT_EXIST], actualResponse);
    }

    @Then("tell client that they are already in the program")
    public void tell_client_that_they_are_already_in_the_program() {
        assertEquals(Client.ENROLLMENT_REPLY_MESSAGES[Client.ENROLLMENT_FAIL_ALREADY_IN_PROGRAM], actualResponse);
    }

    @Then("tell client that they were successfully enrolled in the program")
    public void tell_client_that_they_were_successfully_enrolled_in_the_program() {
        assertEquals(Client.ENROLLMENT_REPLY_MESSAGES[Client.ENROLLMENT_SUCCESS], actualResponse);
        Clients.removeClientFromProgram(username, program);
    }

}
