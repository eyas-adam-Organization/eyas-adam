import io.cucumber.java.en.*;
import org.junit.jupiter.api.AfterAll;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;public class NotifyClients {
    private Clients clients;
    private String actualMessage;
    private String expectedMessage;
    private String notification;
    private String programTitle;
    private Program program;
    private Programs programs;
    public NotifyClients(Clients clients,Programs programs){
        this.clients=clients;
        this.programs=programs;
    }

    @Given("I have an {string} in program {string}")
    public void iHaveAnInProgram(String notification, String programTitle) {
        // Write code here that turns the phrase above into concrete actions
        this.notification=notification;
        this.programTitle=programTitle;

     }
    @When("I notify the clients enrolled in this program")
    public void iNotifyTheClientsEnrolledInThisProgram() throws CloneNotSupportedException, IOException {
        // Write code here that turns the phrase above into concrete actions
        if(notification.equals("update")){
            program=programs.searchForProgram(programTitle);
            Program programBefore=(Program) program.clone();
            String s=programs.UpdateProgram(program.getTitle() ,program.getLevel() ,"111" ,(program.getDuration()+1)+"" ,program.getVideo()==null?"null" : program.getVideo().getPath(),program.getImage()==null?"null":program.getImage().getPath(),program.getDocuments()==null? "null":program.getDocuments().getPath(), program.getPrice()+"");
            program=programs.searchForProgram(programTitle);
            actualMessage=clients.NotifyClients(programBefore,program);
            programs.DeleteProgram(programTitle);
            s=programs.CreateProgram(programBefore.getTitle() ,programBefore.getLevel() ,programBefore.getGoal() ,programBefore.getDuration()+"" ,programBefore.getVideo()==null?"null" : programBefore.getVideo().getPath(),programBefore.getImage()==null?"null":programBefore.getImage().getPath(),programBefore.getDocuments()==null? "null":programBefore.getDocuments().getPath(), programBefore.getPrice()+"");
         }
        else if(notification.equals("remove")){
            program=programs.searchForProgram(programTitle);
            Program programBefore=(Program) program.clone();
            String s=programs.DeleteProgram(program.getTitle());
            program=programs.searchForProgram(programTitle);
            actualMessage=clients.NotifyClients(programBefore,program);

            s=programs.CreateProgram(programBefore.getTitle() ,programBefore.getLevel() ,programBefore.getGoal() ,programBefore.getDuration()+"" ,programBefore.getVideo()==null?"null" : programBefore.getVideo().getPath(),programBefore.getImage()==null?"null":programBefore.getImage().getPath(),programBefore.getDocuments()==null? "null":programBefore.getDocuments().getPath(), programBefore.getPrice()+"");


        }


    }
    @Then("the client {string} should receive the notification {string}")
    public void theClientShouldReceiveTheNotification(String clientID, String notificationToClient) throws FileNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        for (Client client:clients.getClients()){
            if(client.getID()==Integer.parseInt(clientID)){
                assertEquals(notificationToClient, client.getNotification(), "TEST FAILED: Filtered programs do not match expected results.");

            }
        }


     }
    @Then("the system shows {string}")
    public void theSystemShows(String string) {
        // Write code here that turns the phrase above into concrete actions
        expectedMessage=string;
        assertEquals(expectedMessage,actualMessage, "TEST FAILED: Filtered programs do not match expected results.");

    }

    @When("I notify the clients enrolled in this program with no change")
    public void iNotifyTheClientsEnrolledInThisProgramWithNoChange() throws CloneNotSupportedException, IOException {
        // Write code here that turns the phrase above into concrete actions
         program=programs.searchForProgram(programTitle);
            Program programBefore=(Program) program.clone();
            String s=programs.UpdateProgram(program.getTitle() ,program.getLevel() ,program.getGoal() ,program.getDuration()+"" ,program.getVideo()==null?"null" : program.getVideo().getPath(),program.getImage()==null?"null":program.getImage().getPath(),program.getDocuments()==null? "null":program.getDocuments().getPath(), program.getPrice()+"");
            actualMessage=clients.NotifyClients(programBefore,program);



    }
    @Given("I have an {string}")
    public void iHaveAn(String programTitle){
        this.programTitle=programTitle;
    }


    @Given("no clients are enrolled in this program")
    public void noClientsAreEnrolledInThisProgram() {
        // Write code here that turns the phrase above into concrete actions
     }
     @When("I notify for program that does not exist")
     public void iNotifyForProgramThatDoesNotExist() throws CloneNotSupportedException, IOException {
          program=programs.searchForProgram(programTitle);
         Program programBefore;
         if(program==null) programBefore=null;
         else programBefore=(Program) program.clone();
         actualMessage=clients.NotifyClients(programBefore,program);

     }






}
