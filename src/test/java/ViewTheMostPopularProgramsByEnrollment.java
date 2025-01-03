import io.cucumber.java.en.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewTheMostPopularProgramsByEnrollment {
    private ArrayList<ProgramData> programsDataExpected=new ArrayList<>();
    private ArrayList<ProgramData> programsDataActual=new ArrayList<>();
    private String actualMessage;
    private String expectedMessage;


    private Programs programs;
    private Clients clients;
    public   ViewTheMostPopularProgramsByEnrollment(Clients clients,Programs programs){
        this.clients=clients;
        this.programs=programs;
    }
    @Given("the following program enrollment data:")
    public void theFollowingProgramEnrollmentData(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<List<String>> rows = dataTable.asLists(String.class);
        programsDataExpected.clear();

        for (List<String> row : rows) {
             if(rows.indexOf(row)!=0)programsDataExpected.add(new ProgramData(row.get(0),Integer.parseInt(row.get(1))));
        }

    }
    @When("I request the programs sorted by enrollment")
    public void iRequestTheProgramsSortedByEnrollment() {
        // Write code here that turns the phrase above into concrete actions
          actualMessage=programs.ViewTheMostPopularProgramsByEnrollment("0",programsDataActual,clients);

     }
    @Then("the programs should be sorted in descending order of enrollments")
    public void theProgramsShouldBeSortedInDescendingOrderOfEnrollments() {
        // Write code here that turns the phrase above into concrete actions


        expectedMessage="viewed successfully";
        assertEquals(expectedMessage,actualMessage, "TEST FAILED: Filtered programs do not match expected results.");

        assertEquals(programsDataExpected.size(), programsDataActual.size(), "TEST FAILED: Filtered programs do not match expected results.");
        for(int i=0;i<programsDataExpected.size();i++){
            assertEquals(programsDataExpected.get(i), programsDataActual.get(i), "TEST FAILED: Filtered programs do not match expected results.");

        }


    }

    @Given("the following program price data:")
    public void theFollowingProgramPriceData(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<List<String>> rows = dataTable.asLists(String.class);
        programsDataExpected.clear();

        for (List<String> row : rows) {
            if(rows.indexOf(row)!=0) programsDataExpected.add(new ProgramData(row.get(0),Integer.parseInt(row.get(1))));
        }
     }
    @When("I request the programs sorted by price")
    public void iRequestTheProgramsSortedByPrice() {
        // Write code here that turns the phrase above into concrete actions
        programsDataActual.clear();
        actualMessage=programs.ViewTheMostPopularProgramsByEnrollment("1",programsDataActual,clients);

    }
    @Then("the programs should be sorted in descending order of price")
    public void theProgramsShouldBeSortedInDescendingOrderOfPrice() {
        // Write code here that turns the phrase above into concrete actions
        expectedMessage="viewed successfully";
        assertEquals(expectedMessage,actualMessage, "TEST FAILED: Filtered programs do not match expected results.");
        assertEquals(programsDataExpected.size(), programsDataActual.size(), "TEST FAILED: Filtered programs do not match expected results.");
        for(int i=0;i<programsDataExpected.size();i++){
            assertEquals(programsDataExpected.get(i), programsDataActual.get(i), "TEST FAILED: Filtered programs do not match expected results.");

        }

    }




}

