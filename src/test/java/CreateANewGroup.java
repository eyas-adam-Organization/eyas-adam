import io.cucumber.java.en.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CreateANewGroup {
    private Clients clients;

     private String actualMessage;
    private String expectedMessage;
    private Groups groups;
    private String instructorID;
    private String schedules;
    private String time;
    private String groupID;
    private Instructors instructors;
    private Clients allClients;
    public CreateANewGroup(Groups groups,Instructors instructors,Clients allClients) throws FileNotFoundException {

        this.groups=groups;
        this.instructors=instructors;
        this.allClients=allClients;
        groups.loadFromFile(instructors,allClients);
    }


    @Given("i have instructor {string} with schedules {string}  at time {string} and group id {string}")
    public void iHaveInstructorWithSchedulesAtTimeAndGroupId(String instructorID, String schedules, String time ,String groupID) {
        // Write code here that turns the phrase above into concrete actions
        this.instructorID=instructorID;
        this.time=time;
        this.groupID=groupID;
        this.schedules=schedules;

     }
    @When("i create a new group")
    public void iCreateANewGroup() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        actualMessage=groups.CreateGroup(groupID,instructorID,time,schedules);
      }
    @Then("the group will be add")
    public void theGroupWillBeAdd() throws IOException {
        // Write code here that turns the phrase above into concrete actions

         assertEquals(groupID,groups.SearchForGroup(Integer.parseInt(groupID)).getGroupID()+"" , "TEST FAILED: Filtered programs do not match expected results.");

    }
    @Then("the system should display a message for create {string}")
    public void theSystemShouldDisplayAMessageForCreate(String string) {
        // Write code here that turns the phrase above into concrete actions
        expectedMessage=string;
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");

    }
    @Then("the group will not be add")
    public void theGroupWillNotBeAdd() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        assertNull(groups.SearchForGroup(Integer.parseInt(groupID)), "TEST FAILED: Filtered programs do not match expected results.");

    }




}

