import io.cucumber.java.en.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class AnnounceNewProgramsOrSpecialOffers {
    private Clients clients;
        private String actualMessage;
    private String expectedMessage;
    private String announcement;
    private String value;
    public AnnounceNewProgramsOrSpecialOffers(Clients clients){
        this.clients=clients;
     }
    @Given("I have a list of clients and a new Announcement {string} {string}")
    public void iHaveAListOfClientsAndANewAnnouncement(String announcement, String value) {
        // Write code here that turns the phrase above into concrete actions
        this.announcement=announcement;
        this.value=value;

     }
    @When("I send Announcement reminders to the client")
    public void iSendAnnouncementRemindersToTheClient() throws IOException {
        // Write code here that turns the phrase above into concrete actions
       if(announcement.equals("new program")){
           String s=clients.getPrograms().CreateProgram(value,"hard","loos wight","12","null","null","null","15");
           actualMessage=clients.AnnounceNewProgramsOrSpecialOffers(announcement,value);
           s=clients.getPrograms().DeleteProgram(value);

       }
       else {
            actualMessage=clients.AnnounceNewProgramsOrSpecialOffers(announcement,value);
        }

     }
    @Then("the clients should reserve the Announcement")
    public void theClientsShouldReserveTheAnnouncement() throws FileNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        for(Client client:clients.getClients()){
            assertEquals(announcement+":"+value, client.getAnnouncement(), "TEST FAILED: Filtered programs do not match expected results.");

        }
     }
    @Then("the system for announcement display {string}")
    public void theSystemForAnnouncementDisplay(String string) {
        // Write code here that turns the phrase above into concrete actions
        expectedMessage=string;
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");

    }
    @When("I send Announcement a program thar does not exist")
    public void iSendAnnouncementAprogramTharDoesNotExist() throws IOException {
        if(announcement.equals("new program")){
             actualMessage=clients.AnnounceNewProgramsOrSpecialOffers(announcement,value);

        }
        else {
            actualMessage=clients.AnnounceNewProgramsOrSpecialOffers(announcement,value);
        }
    }

}
