import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;


public class ClientMainMenu {

    Clients clients;
    int actualMenu;

    public ClientMainMenu(Clients c){
        clients = c;
    }

    @When("client enters {string}")
    public void client_enters_or(String option) {
        clients.sendFromMainMenu(option);
        actualMenu = clients.currentMenu;
    }

    @Then("send client to {string} menu")
    public void send_client_to_menu(String menuName) {
        assertEquals(menuName, Clients.CLIENT_PROFILE_SEND_TEXTS[actualMenu]);
    }

    @Then("clear the current signed in client")
    public void clear_the_current_signed_in_client() {
        assertNull(clients.activeClient);
    }
}
