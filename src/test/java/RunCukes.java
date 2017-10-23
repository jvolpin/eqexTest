import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Juan on 23/10/2017.
 */
@CucumberOptions(features = "src/test/resources/")
@RunWith(Cucumber.class)
public class RunCukes {
}
