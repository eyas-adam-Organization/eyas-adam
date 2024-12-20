import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;


public class AdminArticleApproval {
    Admin admin;
    boolean approval;

    public AdminArticleApproval(Admin a){
        admin =a;
    }

    @Given("admin rejected")
    public void admin_rejected() {
        approval = false;
    }

    @Given("admin approved")
    public void admin_approved() {
        approval = true;
    }

    @When("admin received {string} {string} from instructor {string}")
    public void admin_received_from_instructor(String type, String data, String instructor) {
        if (approval)
            admin.saveApprovedToFile(type, data, instructor);
    }

    @Then("do not save the {string} {string} from instructor {string}")
    public void do_not_save_the_from_instructor(String type, String data, String instructor) {
        assertFalse(Admin.approvedSaved(type, data, instructor));
    }

    @Then("save the {string} {string} from instructor {string}")
    public void save_the_from_instructor(String type, String data, String instructor) {
        assertTrue(Admin.approvedSaved(type, data, instructor));
    }
}
