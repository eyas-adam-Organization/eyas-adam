import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;



public class clientCompletedProgramRating {

    Clients clients;
    String actualMessage;
    Client client;


    public clientCompletedProgramRating(Clients c){
        clients = c;
    }

    @When("Client {string} gave a rating {string} and review {string} and suggestion {string} for program {string}")
    public void client_gave_a_rating_and_review_and_suggestion_for_program(String username, String rating, String review, String suggestion, String program) {
        client = clients.getClientByUsername(username);
        actualMessage = client.writeReview(program, rating, review, suggestion);
    }

    @Then("Tell the client that they need to complete the program first")
    public void tell_the_client_that_they_need_to_complete_the_program_first() {
        assertEquals(Client.REVIEW_REPLY_MESSAGES[Client.DID_NOT_COMPLETE_PROGRAM_MESSAGE], actualMessage);
    }

    @Then("Tell the client that the rating should be a numerical value")
    public void tell_the_client_that_the_rating_should_be_a_numerical_value() {
        assertEquals(Client.REVIEW_REPLY_MESSAGES[Client.RATING_NOT_NUMERICAL_MESSAGE], actualMessage);
    }

    @Then("Tell the client that the rating should be within the acceptable range")
    public void tell_the_client_that_the_rating_should_be_within_the_acceptable_range() {
        assertEquals(Client.REVIEW_REPLY_MESSAGES[Client.RATING_OUTSIDE_RANGE_MESSAGE], actualMessage);
    }

    @Then("Thank the client for the their liking of the program and the suggestion")
    public void thank_the_client_for_the_their_liking_of_the_program_and_the_suggestion() {
        assertEquals(Client.REVIEW_REPLY_MESSAGES[Client.REVIEW_GOOD_MESSAGE], actualMessage);
    }

    @Then("Thank the client for the their review and say that you'll try to fix the problem")
    public void thank_the_client_for_the_their_review_and_say_that_you_ll_try_to_fix_the_problem() {
        assertEquals(Client.REVIEW_REPLY_MESSAGES[Client.REVIEW_OKAY_MESSAGE], actualMessage);
    }

    @Then("Apologize to the client and assure them that their suggestion will be considered")
    public void apologize_to_the_client_and_assure_them_that_their_suggestion_will_be_considered() {
        assertEquals(Client.REVIEW_REPLY_MESSAGES[Client.REVIEW_BAD_MESSAGE], actualMessage);
    }
}
