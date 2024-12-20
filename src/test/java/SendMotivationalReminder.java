import io.cucumber.java.en.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendMotivationalReminder {
    private Clients clients;
    private String limit;
    private String actualMessage;
    private String expectedMessage;
    public SendMotivationalReminder(Clients clients){
        this.clients=clients;
    }
     @Given("a client's completion rate is below {string}")
    public void aClientSCompletionRateIsBelow(String limit) {
        // Write code here that turns the phrase above into concrete actions
         this.limit=limit;

     }
    @When("I send motivational reminders to the client")
    public void iSendMotivationalRemindersToTheClient() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        actualMessage=clients.SendMotivationalReminder(limit);
     }
    @Then("the client {string}  shouled reserve the {string} and the system display {string}")
    public void theClientShouledReserveTheAndTheSystemDisplay(String id, String reminder, String message) throws FileNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        expectedMessage=message;
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");
        for (Client client:clients.getClients()){
            if(Integer.parseInt(id)==client.getID()){

                assertEquals(reminder, client.getMotivationalReminder(), "TEST FAILED: Filtered programs do not match expected results.");

            }
        }

    }
    @Then("the system display {string}")
    public void theSystemDisplay(String message) {
        expectedMessage=message;
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");
     }



}
