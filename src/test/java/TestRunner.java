import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = {"classpath:feature_files/"}, glue = {
        "StepDefs" , "core"}, tags = "@Client", plugin = {"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm", "junit:Cucumber.xml"})
public class TestRunner {

    @SuppressWarnings("unchecked")
    @BeforeClass
    public static void setup() {

    }

}
