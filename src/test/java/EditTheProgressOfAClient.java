import io.cucumber.java.en.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class EditTheProgressOfAClient {
    private Clients clients;
    private String type;
    private String value;
    private String ID;
    private String actualMessage;
    private String expectedMessage;
    public EditTheProgressOfAClient(Clients clients){
        this.clients=clients;
    }
    @Given("the type of the edited value is {string}")
    public void theTypeOfTheEditedValueIs(String type) {
        // Write code here that turns the phrase above into concrete actions
        this.type=type;


     }
    @Given("the new value is {string}")
    public void theNewValueIs(String value) {
        // Write code here that turns the phrase above into concrete actions
        this.value=value;
     }
    @When("I edit the progress of client {string}")
    public void iEditTheProgressOfClient(String ID) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        UniversalMethods.getFile("src/main/resources/clients_with_progress.txt");
        this.ID=ID;
        actualMessage=clients.EditTheProgressOfAClient(type,value,ID);
        UniversalMethods.returnFile("src/main/resources/clients_with_progress.txt");


    }
    @Then("the system updates the progress data and displays a confirmation message {string}")
    public void theSystemUpdatesTheProgressDataAndDisplaysAConfirmationMessage(String string) {
        // Write code here that turns the phrase above into concrete actions
        expectedMessage=string;
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");

    }

    @Then("the system displays an error message {string}")
    public void theSystemDisplaysAnErrorMessage(String string) {
        // Write code here that turns the phrase above into concrete actions
        expectedMessage=string;

        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");

    }




}
