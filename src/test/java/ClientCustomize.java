import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClientCustomize {
    public String actualMessage;

    Clients clients;
    public ClientCustomize(Clients c){
        clients = c;
    }

    @Given("the username is {string}")
    public void the_username_is(String username) {
        clients.setActiveClient(username);
    }

    @When("client entered {string} {string} {string} {string} {string} {string} {string} for the values respectively")
    public void client_entered_for_the_values_respectively(String age, String weight, String BMI, String goalBMI, String goalWeight, String preferences, String restrictions) {
        actualMessage = clients.updateValues(age, weight, BMI, goalBMI, goalWeight, preferences, restrictions);
    }

    @Then("Tell the client that the age should be a numerical value")
    public void tell_the_client_that_the_age_should_be_a_numerical_value() {
        assertEquals(Clients.ERROR_MESSAGES[Clients.ERROR_CODE_AGE_N], actualMessage);
    }

    @Then("Tell the client that the age should be a value within the acceptable range")
    public void tell_the_client_that_the_age_should_be_a_value_within_the_acceptable_range() {
        assertEquals(Clients.ERROR_MESSAGES[Clients.ERROR_CODE_AGE_R], actualMessage);
    }

    @Then("Tell the client that the weight should be a numerical value")
    public void tell_the_client_that_the_weight_should_be_a_numerical_value() {
        assertEquals(Clients.ERROR_MESSAGES[Clients.ERROR_CODE_WEIGHT_N], actualMessage);
    }

    @Then("Tell the client that the weight should be a value within the acceptable range")
    public void tell_the_client_that_the_weight_should_be_a_value_within_the_acceptable_range() {
        assertEquals(Clients.ERROR_MESSAGES[Clients.ERROR_CODE_WEIGHT_R], actualMessage);
    }

    @Then("Tell the client that the BMI should be a numerical value")
    public void tell_the_client_that_the_bmi_should_be_a_numerical_value() {
        assertEquals(Clients.ERROR_MESSAGES[Clients.ERROR_CODE_BMI_N], actualMessage);
    }

    @Then("Then Tell the client that the BMI should be a value within the acceptable range")
    public void then_tell_the_client_that_the_bmi_should_be_a_value_within_the_acceptable_range() {
        assertEquals(Clients.ERROR_MESSAGES[Clients.ERROR_CODE_BMI_R], actualMessage);
    }

    @Then("Tell the client that the goal BMI should be a numerical value")
    public void tell_the_client_that_the_goal_bmi_should_be_a_numerical_value() {
        assertEquals(Clients.ERROR_MESSAGES[Clients.ERROR_CODE_GOAL_BMI_N], actualMessage);
    }

    @Then("Then Tell the client that the goal BMI should be a value within the acceptable range")
    public void then_tell_the_client_that_the_goal_bmi_should_be_a_value_within_the_acceptable_range() {
        assertEquals(Clients.ERROR_MESSAGES[Clients.ERROR_CODE_GOAL_BMI_R], actualMessage);
    }

    @Then("Tell the client that the goal weight should be a numerical value")
    public void tell_the_client_that_the_goal_weight_should_be_a_numerical_value() {
        assertEquals(Clients.ERROR_MESSAGES[Clients.ERROR_CODE_GOAL_WEIGHT_N], actualMessage);
    }

    @Then("Tell the client that the goal weight should be a value within the acceptable range")
    public void tell_the_client_that_the_goal_weight_should_be_a_value_within_the_acceptable_range() {
        assertEquals(Clients.ERROR_MESSAGES[Clients.ERROR_CODE_GOAL_WEIGHT_R], actualMessage);
    }

//    @Then("update client data {string} {string} {string} {string} {string} {string} {string}")
//    public void update_client_data( String age, String weight, String BMI, String goalBMI, String goalWeight, String preferences, String restrictions) {
//        assertTrue(clients.wasUpdated(age, weight, BMI, goalBMI, goalWeight, preferences, restrictions));
//    }

    @Then("Tell the client that the update was successful")
    public void tell_the_client_that_the_update_was_successful() {
        assertEquals(Clients.ERROR_MESSAGES[Clients.SUCCESS_CODE], actualMessage);
    }

    @Then("send client to {string} customize")
    public void send_client_to_customize(String menu) {
        assertEquals(menu, Clients.CLIENT_PROFILE_SEND_TEXTS[clients.currentMenu]);
    }
}
