import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class AdminClientDelete {
    Admin admin;
    String actualOutput;

    public AdminClientDelete(Admin a){
        admin = a;
    }



    @When("admin enters username {string}")
    public void admin_enters_username(String username) {
        actualOutput = admin.deleteClient(username);
    }

    @Then("tell  the admin that this username does not exist")
    public void tell_the_admin_that_this_username_does_not_exist() {
        assertEquals(Admin.DELETE_FAILED_MESSAGE, actualOutput);
    }

    @Then("tell the admin that the deletion was successful")
    public void tell_the_admin_that_the_deletion_was_successful() {
        assertEquals(Admin.DELETE_SUCCESS_MESSAGE, actualOutput);
        admin.getUserBackFromArchive(true);
    }
}
