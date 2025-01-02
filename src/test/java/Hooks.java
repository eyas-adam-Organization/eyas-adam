import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import java.io.IOException;

public class Hooks {
    public static String filename;

    @BeforeAll
    public static void beforeAllScenarios() throws IOException {
        UniversalMethods.getFile("src/main/resources/clients_with_progress.txt");
        UniversalMethods.getFile("src/main/resources/groups.txt");
        UniversalMethods.getFile("src/main/resources/instructors.txt");
        UniversalMethods.getFile("src/main/resources/programs.txt");
        UniversalMethods.getFile("src/main/resources/programsss.txt");

    }

    @AfterAll
    public static void afterAllScenarios() {
        UniversalMethods.returnFile("src/main/resources/programsss.txt");
        UniversalMethods.returnFile("src/main/resources/programs.txt");
        UniversalMethods.returnFile("src/main/resources/instructors.txt");
        UniversalMethods.returnFile("src/main/resources/groups.txt");
        UniversalMethods.returnFile("src/main/resources/clients_with_progress.txt");
    }


}