import io.cucumber.java.en.*;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class ClientPrograms {

    Clients clients;

    public ClientPrograms(Clients c){
        clients = c;
    }

    ArrayList <Program> programs = new ArrayList<>();
    ArrayList <Program> actualPrograms;
    @Given("a list of programs")
    public void a_list_of_programs() throws FileNotFoundException {
        programs = clients.getAllPrograms();
    }

    @When("the client does not use a filter")
    public void the_client_does_not_use_a_filter() throws FileNotFoundException {
        actualPrograms = clients.getPrograms(null, null, false);
    }

    @Then("show all programs available")
    public void show_all_programs_available(){
        boolean equality = programs.size() == actualPrograms.size();
        if (equality)
            for(int i = 0; i < programs.size(); i++){
                if(Objects.equals(programs.get(i), actualPrograms.get(i)))
                    equality = false;
            }
        assertTrue(equality, "TEST FAILED: Filtered programs do not match expected results.");
    }

    @When("the client chooses {string} {string}")
    public ArrayList<Program> the_client_chooses(String filterType, String filterValue) throws FileNotFoundException {
        if(filterType.equalsIgnoreCase("difficulty")){
            actualPrograms = clients.getPrograms(filterValue, null, false);
        } else if(filterType.equalsIgnoreCase("focus area"))
            actualPrograms = clients.getPrograms(null, filterValue, false);
        return actualPrograms;
    }


    @Then("show programs related to {string} {string}")
    public void show_programs_related_to(String filterType, String filterValue){
        boolean valid = true;
        if(filterType.equalsIgnoreCase("difficulty")){
            for (Program program: actualPrograms){
                if(!program.getLevel().equalsIgnoreCase(filterValue)){
                    valid = false;
                    break;
                }
            }
        }
        else if (filterType.equalsIgnoreCase("focus area")){
            for (Program program: actualPrograms){
                if(!program.getFocus().equalsIgnoreCase(filterValue)){
                    valid = false;
                    break;
                }
            }
        }
        assertTrue(valid, "TEST FAILED: Filtered programs do not match expected results.");
    }

    @When("the client chooses difficulty {string} and Focus Area {string}")
    public void the_client_chooses_difficulty_and_focus_area(String difficulty, String focus) throws FileNotFoundException {
        actualPrograms =  clients.getPrograms(difficulty, focus, false);
    }

    @Then("show programs related to {string} and {string}")
    public void show_programs_related_to_and(String difficulty, String focus){
        boolean valid = true;
        for (Program actualProgram : actualPrograms) {
            if (!actualProgram.getLevel().equalsIgnoreCase(difficulty) || !actualProgram.getFocus().equalsIgnoreCase(focus)) {
                valid = false;
                break;
            }
        }
        assertTrue(valid, "TEST FAILED: Filtered programs do not match expected results.");
    }

    @When("the client choose to go back")
    public void the_client_choose_to_go_back() {
        actualPrograms = clients.getPrograms(null, null, true);
    }

    @Then("take the client to the main profile")
    public void take_the_client_to_the_main_profile() {
        assertEquals(Clients.CLIENT_PROFILE_CODE, clients.currentMenu);
    }

}
