import io.cucumber.java.en.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class GenerateReports {
    private Programs programs;
    private  Clients clients;
    private Admin admin;
    private String path;
    private String type;
    private ArrayList<ReporTemp> reportTempsExpected;
    private ArrayList<ReporTemp> reportTempsActual;
    private String totalExpected;
    private String totalActual;



    private String actualMessage;
    private String expectedMessage;
    public GenerateReports(Programs programs, Clients clients,Admin admin){
        this.admin=admin;
        this.programs=programs;
        this.clients=clients;
        reportTempsExpected=new ArrayList<>();
        reportTempsActual=new ArrayList<>();
    }

    @Given("the data of the report with type {string} and path {string} is as the following")
    public void theDataOfTheReportWithTypeIsAsTheFollowing(String type,String path, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        this.path=path;
        this.type=type;
        List<List<String>> rows = dataTable.asLists(String.class);
        reportTempsExpected.clear();
        if(type.equals("0")) {
            for (List<String> row : rows) {
                if (rows.indexOf(row) != 0) reportTempsExpected.add(new ReporTemp(Integer.parseInt(row.get(0)), row.get(1), row.get(2)));
            }
        }
        else if(type.equals("1")){
            for (List<String> row : rows) {
                if (rows.indexOf(row) != 0) reportTempsExpected.add(new ReporTemp(row.get(0),row.get(1), row.get(2)));
            }

        }
     }
    @When("i generate of client progress")
    public void iGenerateOfClientProgress() throws FileNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        admin.GenerateReports(clients,programs,Integer.parseInt(type),path);
     }
    @Then("the report is created successfully")
    public void theReportIsCreatedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
        File file = new File(path);
        reportTempsActual.clear();

        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            String []lines= text.split("\n");
            ArrayList<String>Lines=new ArrayList<>();
             for(String line:lines) {
                 String s= "------------------------------------------------------------------------------------------------\r";
                 if(!line.equals(s))Lines.add(line);
              }
             for (String Line:Lines) {
                 String [] parts=Line.split("\\|");
                 if(type.equals("0")) {
                     if (Lines.indexOf(Line) != 0) reportTempsActual.add(new ReporTemp(Integer.parseInt(parts[0].trim()), parts[1].trim(), parts[2].trim()));
                 }
                 else if(type.equals("1")){
                     if (Lines.indexOf(Line) != 0 && Lines.indexOf(Line)!=Lines.size()-1) reportTempsActual.add(new ReporTemp(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                     else if(Lines.indexOf(Line)==Lines.size()-1)totalActual=Line.split(":")[1];
                 }


             }
             if(type.equals("0"))for(int i=0;i<reportTempsExpected.size();i++){
                 assertEquals(reportTempsExpected.get(i).getClientID(), reportTempsActual.get(i).getClientID(), "TEST FAILED: Filtered programs do not match expected results.");
                 assertEquals(reportTempsExpected.get(i).getCompletion(), reportTempsActual.get(i).getCompletion(), "TEST FAILED: Filtered programs do not match expected results.");
                 assertEquals(reportTempsExpected.get(i).getAttendance(), reportTempsActual.get(i).getAttendance(), "TEST FAILED: Filtered programs do not match expected results.");

             }


           else if(type.equals("1"))for(int i=0;i<reportTempsExpected.size();i++){
                 assertEquals(reportTempsExpected.get(i).getProgramTitle(), reportTempsActual.get(i).getProgramTitle(), "TEST FAILED: Filtered programs do not match expected results.");
                 assertEquals(reportTempsExpected.get(i).getNumOfClients(), reportTempsActual.get(i).getNumOfClients(), "TEST FAILED: Filtered programs do not match expected results.");
                 assertEquals(reportTempsExpected.get(i).getProgramPrice(), reportTempsActual.get(i).getProgramPrice(), "TEST FAILED: Filtered programs do not match expected results.");

             }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Then("with total\"{int}\"")
    public void withTotal(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        totalExpected=int1+"";
        assertEquals(totalExpected,totalActual.trim(), "TEST FAILED: Filtered programs do not match expected results.");

    }






}
