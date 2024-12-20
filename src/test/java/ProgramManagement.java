import io.cucumber.java.en.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgramManagement {
     private Programs  programs;
    private String actualMessage;
    private String expectedMessage;
    private String videoPath;
    private String imagePath;
    private String docPath;
    private int j;

    public ProgramManagement( Programs  programs){
         this.programs=programs;

    }
    @Given("the instructor is logged into the system")
    public void theInstructorIsLoggedIntoTheSystem() {
      }
    @Given("the instructor has chosen the Program Management option")
    public void theInstructorHasChosenTheProgramManagementOption() {
        // Write code here that turns the phrase above into concrete actions
     }
    @When("the instructor creates a new program {string} {string} {string} {string} {string} {string} {string} {string}.")
    public void theInstructorCreatesANewProgram(String title, String level, String goal,String duration, String video, String image, String documents, String price) throws IOException {
        actualMessage= programs.CreateProgram(title,level, goal, duration, video, image, documents, price);
        expectedMessage="the program has been add successfully";

    }

    @Then("the program should be added to the available programs list")
    public void theProgramShouldBeAddedToTheAvailableProgramsList() {
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");
      }

    @When("the instructor creates an existing program {string} {string} {string} {string} {string} {string} {string} {string}.")
    public void theInstructorCreatesAnExistingProgram(String title, String level, String goal,String duration, String video, String image, String documents, String price) throws IOException {
         actualMessage= programs.CreateProgram(title,level, goal, duration, video, image, documents, price);
        expectedMessage="the program is already added";

    }
    @Then("the system should display an error message")
    public void theSystemShouldDisplayAnErrorMessage() {
          assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");
      }


    @Given("there is an existing program")
    public void thereIsAnExistingProgram() {
        // Write code here that turns the phrase above into concrete actions
     }
    @When("the instructor updates the details of an existing program {string} {string} {string} {string} {string} {string} {string} {string}.")
    public void theInstructorUpdatesTheDetailsOfAnExistingProgram(String title, String level, String goal,String duration, String video, String image, String documents, String price) {
        actualMessage= programs.UpdateProgram(title,level, goal, duration, video, image, documents, price);
        expectedMessage="the program has been updated successfully";

    }
    @Then("the changes should be saved successfully")
    public void theChangesShouldBeSavedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");

    }

    @When("the instructor updates the details of program that does not existent {string} {string} {string} {string} {string} {string} {string} {string}.")
    public void theInstructorUpdatesTheDetailsOfProgramThatDoesNotExistent(String title, String level, String goal,String duration, String video, String image, String documents, String price) {
        actualMessage= programs.UpdateProgram(title,level, goal, duration, video, image, documents, price);
        expectedMessage="the program does not exist";

    }

    @When("the instructor deletes the details of an existing program  {string}.")
    public void theInstructorDeletesTheDetailsOfAnExistingProgram(String title) {
        // Write code here that turns the phrase above into concrete actions
        actualMessage= programs.DeleteProgram(title);
        expectedMessage="the program has been removed successfully";

     }
    @Then("the program should be removed successfully")
    public void theProgramShouldBeRemovedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");

    }

    @When("the instructor deletes the details of program that does not existent {string}.")
    public void theInstructorDeletesTheDetailsOfProgramThatDoesNotExistent(String title) {
        // Write code here that turns the phrase above into concrete actions
        actualMessage= programs.DeleteProgram(title);
        expectedMessage="the program does not exist";
     }


    @Given("I have a video file path {string}")
    public void iHaveAVideoFilePath(String videoPath) {
        this.videoPath=videoPath;
     }
    @Given("I have an image file path {string}")
    public void iHaveAnImageFilePath(String imagePath) {
        // Write code here that turns the phrase above into concrete actions
        this.imagePath=imagePath;
     }
    @Given("I have a document file path {string}")
    public void iHaveADocumentFilePath(String docPath) {
        // Write code here that turns the phrase above into concrete actions
        j=0;
        this.docPath=docPath;
       }
    @When("I pass these file paths to the create function {string}")
    public void iPassTheseFilePathsToCreateTheFunction(String title) {
        // Write code here that turns the phrase above into concrete actions
        actualMessage=programs.CreateProgram(title,"hard","bbb","5",videoPath,imagePath,docPath,"50");
     }
    @Then("the function returns an error for the {string} file")
    public void theFunctionReturnsAnErrorForTheFile(String string) {
        expectedMessage=string;

        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");
    }
    @When("I pass these file paths to the update function {string}")
    public void iPassTheseFilePathsToTheUpdateFunction(String title) {
        // Write code here that turns the phrase above into concrete actions
        actualMessage=programs.UpdateProgram(title,"hard","bbb","5",videoPath,imagePath,docPath,"50");

    }




}
