import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class AdminInstructorDelete {
    Admin admin;
    String actualOutput;

    public AdminInstructorDelete(Admin a){
        admin = a;
    }

    @When("admin enters instructor username {string}")
    public void admin_enters_instructor_username(String username) {
        actualOutput = admin.deleteInstructor(username);
    }

    @Then("tell the admin that this instructor username does not exist")
    public void tell_the_admin_that_this_instructor_username_does_not_exist() {
        assertEquals(Admin.DELETE_FAILED_MESSAGE, actualOutput);
    }

    @Then("tell the admin that the deletion was  successful")
    public void tell_the_admin_that_the_deletion_was_successful() {
        assertEquals(Admin.DELETE_SUCCESS_MESSAGE, actualOutput);
        admin.getUserBackFromArchive(false);
    }
}
