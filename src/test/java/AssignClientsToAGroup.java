import io.cucumber.java.en.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class AssignClientsToAGroup {
    private Clients clients;
    private Instructors instructors;
     private String actualMessage;
    private String expectedMessage;
    private String clientID;
    private String groupID;
    private Groups groups;

    public AssignClientsToAGroup(Groups groups,Clients clients,Instructors instructors) throws FileNotFoundException {
        this.groups=groups;
        this.clients=clients;
        this.instructors=instructors;
        groups.loadFromFile(instructors,clients);
    }
    @Given("i have a client {string} and group {string}")
    public void iHaveAClientAndGroup(String clientID, String groupID) {
        // Write code here that turns the phrase above into concrete actions
        this.groupID=groupID;
        this.clientID=clientID;

    }
    @When("i add the client to the group")
    public void iAddTheClientToTheGroup() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        actualMessage=groups.AssignClientsToAGroup(clientID,groupID);
    }
    @Then("the client must be added")
    public void theClientMustBeAdded() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        boolean flag=false;
        for(Client client:groups.SearchForGroup(Integer.parseInt(groupID)).getClients(instructors,clients)){
            if(client.getID()==Integer.parseInt(clientID)){
                flag=true;
                break;
            }
        }
        assertTrue(flag, "TEST FAILED: Filtered programs do not match expected results.");
    }
    @Then("the system display the message {string}")
    public void theSystemDisplayTheMessage(String string) {
        // Write code here that turns the phrase above into concrete actions
        expectedMessage=string;
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");

    }
    @Then("the client will not be added")
    public void theClientWillNotBeAdded() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        boolean flag=true;
        Group group =groups.SearchForGroup(Integer.parseInt(groupID));
        if(group==null)assertNull(group, "TEST FAILED: Filtered programs do not match expected results.");
        else {
            for(Client client:group.getClients(instructors,clients)){
                if(client.getID()==Integer.parseInt(clientID)){
                    flag=false;
                    break;
                }
            }
        }
        assertTrue(flag, "TEST FAILED: Filtered programs do not match expected results.");

     }

    @Then("the client will not be duplicated")
    public void theClientWillNotBeDuplicated() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        int count=0;
        Group group =groups.SearchForGroup(Integer.parseInt(groupID));
        if(group==null)assertNull(group, "TEST FAILED: Filtered programs do not match expected results.");
        else {
            for(Client client:group.getClients(instructors,clients)){
                if(client.getID()==Integer.parseInt(clientID)){
                    count++;

                }
            }
        }
        assertEquals(1, count, "TEST FAILED: Filtered programs do not match expected results.");

    }




}
