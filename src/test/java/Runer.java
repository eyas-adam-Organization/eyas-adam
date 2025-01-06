
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/Features/"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        plugin = {"html: target/cucumber.html"}

)
public class Runer {
}
