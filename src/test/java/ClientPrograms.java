import io.cucumber.java.en.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class ClientPrograms {
//
//    Client client;
//
//    ClientPrograms(){
//        client = new Client();
//    }

    ArrayList <Program> programs = new ArrayList<>();
    @Given("a list of programs")
    public void a_list_of_programs() throws FileNotFoundException {
        File file = new File("src/main/resources/programs.txt");
        Scanner scanner = new Scanner(file);
        String curLine;
        while (scanner.hasNextLine()){
            curLine = scanner.nextLine();
            String [] array =  curLine.split(",");
            Program program = new Program(array);
            programs.add(program);
        }
    }

    @When("the client does not use a filter")
    public void the_client_does_not_use_a_filter() {
    }

    @Then("show all programs available")
    public void show_all_programs_available() throws FileNotFoundException {
        ArrayList <Program> actualPrograms = Client.getPrograms(null, null);

        boolean equality = programs.size() == actualPrograms.size();
        if (equality)
            for(int i = 0; i < programs.size(); i++){
                if(Objects.equals(programs.get(i), actualPrograms.get(i)))
                    equality = false;
            }
        assertTrue(equality, "TEST FAILED: Filtered programs do not match expected results.");
    }

    @When("the client chooses {string} {string}")
    public void the_client_chooses(String filterType, String filterValue) {
        ArrayList <Program> expectedPrograms = new ArrayList<>();
        if(filterType.equalsIgnoreCase("Difficulty")){
            for(Program program: programs){
                if(filterValue.equalsIgnoreCase(program.getLevel())){
                    expectedPrograms.add(program);
                }
            }
        } else
        if (filterType.equalsIgnoreCase("Focus Area")){
            for(Program program: programs){
                if(filterValue.equalsIgnoreCase(program.getFocus())){
                    expectedPrograms.add(program);
                }
            }
        }
        programs = new ArrayList<>(expectedPrograms);
    }

    @Then("show programs related to {string} {string}")
    public void show_programs_related_to(String filterType, String filterValue) throws FileNotFoundException {
        ArrayList <Program> actualPrograms;
        if (filterType.equalsIgnoreCase("difficulty")){
            actualPrograms = Client.getPrograms(filterValue, null);
        }
        else {
            actualPrograms = Client.getPrograms(null, filterValue);
        }

        boolean equality = programs.size() == actualPrograms.size();
        if (equality)
            for(int i = 0; i < programs.size(); i++){
                if(Objects.equals(programs.get(i), actualPrograms.get(i)))
                    equality = false;
            }
        assertTrue(equality, "TEST FAILED: Filtered programs do not match expected results.");

    }

    @Then("show programs related to {string} and {string}")
    public void show_programs_related_to_and(String difficulty, String focus) throws FileNotFoundException {
        ArrayList <Program> actualPrograms = Client.getPrograms(difficulty, focus);

        boolean equality = programs.size() == actualPrograms.size();
        if (equality)
            for(int i = 0; i < programs.size(); i++){
                if(Objects.equals(programs.get(i), actualPrograms.get(i)))
                    equality = false;
            }
        assertTrue(equality, "TEST FAILED: Filtered programs do not match expected results.");
    }
}
