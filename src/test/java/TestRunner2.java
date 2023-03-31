import core.DockerHandling;
import core.Hooks;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;


import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = {"classpath:feature_files/"}, glue = {
        "StepDefs" , "core"}, tags = "@Client", plugin = {"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm", "junit:Cucumber.xml"})



public class TestRunner2 extends AbstractTestNGCucumberTests {
    DockerHandling dockerHandling = new DockerHandling();


    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

//    @BeforeTest
//    public void startDockerHub() throws IOException, InterruptedException {
//        if (Hooks.isServerUP() == false) {
//            // if false which means server is down
//            dockerHandling.startServer();
//            //isServerUP() should be true after startServer()
//            System.out.println("Server Started");
//            dockerHandling.scaleInstances();
//        }
//    }


//    @AfterTest
//    public void endDockerHub() throws IOException, InterruptedException {
//        if (Hooks.isServerUP() == true) {
//            dockerHandling.stopServer();
//            //isServerUp() should be false after stopServer()
//            System.out.println("Server Quit");
//        }
//    }


}