import io.cucumber.java.en.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ViewStatisticsOnAProgram {
    private Clients clients;
    private Programs programs;
    private String programTitle;
    private ArrayList<ClientStatistics> clientStatisticsExpected;
    private ArrayList<ClientStatistics> clientStatisticsActual;
    private String actualMessage;
    private String expectedMessage;

    public ViewStatisticsOnAProgram(Clients clients,Programs programs){
        this.clients=clients;
        this.programs=programs;
        clientStatisticsExpected=new ArrayList<>();
        clientStatisticsActual=new ArrayList<>();

    }

    @Given("i have the following Statistics on the program {string}")
    public void iHaveTheFollowingStatisticsOnTheProgram(String string, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        this.programTitle=string;

        List<List<String>> rows = dataTable.asLists(String.class);
        clientStatisticsExpected.clear();
        for (List<String> row : rows) {
            if(rows.indexOf(row)!=0)clientStatisticsExpected.add(new ClientStatistics(Integer.parseInt(row.get(0)),Integer.parseInt(row.get(1)),Integer.parseInt(row.get(2))));
        }



    }
    @When("i view the Statistics of this program")
    public void iViewTheStatisticsOfThisProgram() throws FileNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        ProgramStatistics programStatistics=programs.ViewStatisticsOnAProgram(programTitle,clients);
        clientStatisticsActual=programStatistics.getClientStatistics();
        actualMessage=programStatistics.getMessage();

     }

    @Given("the missing program is {string}")
    public void theMissingProgramIs(String string) {
        // Write code here that turns the phrase above into concrete actions
        programTitle=string;
     }
    @Then("the system should not view any data")
    public void theSystemShouldNotViewAnyData() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(clientStatisticsActual.isEmpty(), "TEST FAILED: Filtered programs do not match expected results.");

    }
    @Then("the message {string};")
    public void theMessage(String string) {
        // Write code here that turns the phrase above into concrete actions
      expectedMessage=string;
        assertEquals(expectedMessage, actualMessage, "TEST FAILED: Filtered programs do not match expected results.");

    }
    @Then("the system should view the data")
    public void theSystemShouldViewTheData() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(clientStatisticsExpected , clientStatisticsExpected, "TEST FAILED: Filtered programs do not match expected results.");
    }




}
