import io.cucumber.java.en.*;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MonitorClientProgress {
    private Clients clients;
    private String actualMessage;
    private String expectedMessage;
    public MonitorClientProgress(Clients clients){
        this.clients=clients;
    }
    @Given("I have a list of clients with progress data")
    public void iHaveAListOfClientsWithProgressData() {
        // Write code here that turns the phrase above into concrete actions
     }
    @When("I view the progress details for client {string}")
    public void iViewTheProgressDetailsForClient(String ID) throws FileNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        actualMessage=clients.MonitorClientProgress(ID);

     }
    @Then("I should see the completion rate {string} and attendance record {string}")
    public void iShouldSeeTheCompletionRateAndAttendanceRecord(String CompletionRate, String AttendanceRecord) {
        // Write code here that turns the phrase above into concrete actions
        expectedMessage=CompletionRate+","+AttendanceRecord;
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");
    }

    @Given("I have a list of clients with incomplete progress data")
    public void iHaveAListOfClientsWithIncompleteProgressData() {
        // Write code here that turns the phrase above into concrete actions
     }
    @Then("I should see a warning message {string}")
    public void iShouldSeeAWarningMessage(String string) {
        expectedMessage=string;
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");
     }



}
