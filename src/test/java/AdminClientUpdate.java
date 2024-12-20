import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class AdminClientUpdate {
    Admin admin;
    String actualOutput;

    public AdminClientUpdate(Admin a){
        admin = a;
    }

    @When("admin enters username {string} and password {string}")
    public void admin_enters_username_and_password(String username, String password) {
        actualOutput = admin.updateToClient(username, password);
    }

    @Then("tell the admin that this username does not exist")
    public void tell_the_admin_that_this_username_does_not_exist() {
        assertEquals(Admin.UPDATE_MESSAGES[Admin.UPDATE_NO_USER_CODE], actualOutput);
    }

    @Then("tell the admin that this password is too short")
    public void tell_the_admin_that_this_password_is_too_short() {
        assertEquals(Admin.UPDATE_MESSAGES[Admin.UPDATE_PASSWORD_SHORT_CODE], actualOutput);
    }

    @Then("tell the admin that the update to the password was successful")
    public void tell_the_admin_that_the_update_to_the_password_was_successful() {
        assertEquals(Admin.UPDATE_MESSAGES[Admin.UPDATE_SUCCESS_CODE], actualOutput);
    }

}
