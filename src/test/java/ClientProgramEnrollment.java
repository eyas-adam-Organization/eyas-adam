import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientProgramEnrollment {
    Clients clients;
    String username;
    String program;


    public ClientProgramEnrollment(Clients c){
        this.clients = c;
    }

    String actualResponse;


    @When("A registered client {string} tries to enroll in a program {string}")
    public void a_registered_client_tries_to_enroll_in_a_program(String username, String program) {
        clients.setActiveClient(username);
        actualResponse = clients.enrollInProgram(program);
        this.username = username;
        this.program = program;
    }

    @Then("tell client that this program is not a registered program")
    public void tell_client_that_this_program_is_not_a_registered_program() {
        assertEquals(Clients.ENROLLMENT_REPLY_MESSAGES[Clients.ENROLLMENT_FAIL_PROGRAM_DOES_NOT_EXIST], actualResponse);
    }

    @Then("tell client that they are already in the program")
    public void tell_client_that_they_are_already_in_the_program() {
        assertEquals(Clients.ENROLLMENT_REPLY_MESSAGES[Clients.ENROLLMENT_FAIL_ALREADY_IN_PROGRAM], actualResponse);
    }

    @Then("tell client that they were successfully enrolled in the program")
    public void tell_client_that_they_were_successfully_enrolled_in_the_program() {
        assertEquals(Clients.ENROLLMENT_REPLY_MESSAGES[Clients.ENROLLMENT_SUCCESS], actualResponse);
        Clients.removeClientFromProgram(username, program);
    }

    @Then("send  client to {string}")
    public void send_client_to(String menu) {
        assertEquals(menu, Clients.CLIENT_PROFILE_SEND_TEXTS[clients.currentMenu]);
    }
}
