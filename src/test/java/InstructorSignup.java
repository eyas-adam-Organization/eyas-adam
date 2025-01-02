import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;


public class InstructorSignup {
    Instructors instructors;
    String actualResponse;

    public InstructorSignup(Instructors i){
        instructors = i;
    }

    @When("instructor entered username {string} and password {string}")
    public void instructor_entered_username_and_password(String username, String password) {
        actualResponse = instructors.instructorSignup(username, password);
    }

    @Then("tell instructor that the username is used")
    public void tell_instructor_that_the_username_is_used() {
        assertEquals(Instructors.FAILED_SIGNUP_USERNAME_MESSAGE, actualResponse);
    }

    @Then("tell the instructor that the password is short")
    public void tell_the_instructor_that_the_password_is_short() {
        assertEquals(Instructors.FAILED_SIGNUP_PASSWORD_MESSAGE, actualResponse);
    }

    @Then("tell the instructor that the account was created successfully")
    public void tell_the_instructor_that_the_account_was_created_successfully() {
        assertEquals(Instructors.SUCCESS_SIGNUP_MESSAGE, actualResponse);
    }

    @Then("add the instructor {string} to the list of instructors")
    public void add_the_instructor_to_the_list_of_instructors(String username) {
        assertTrue(Instructors.instructorExists(username));
        Instructors.removeInstructor(username);
    }

}
