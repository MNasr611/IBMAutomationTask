package core;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;


import java.io.IOException;


public class Hooks {
    private static ThreadLocal<WebDriver> driver;
    private final Config config = new Config();
    private DriverFactory factory = new DriverFactory();
    private static boolean serverUP = false;

    public static boolean isServerUP() {
        return serverUP;
    }

    public static void setServerStatus(boolean serverUP) {
        Hooks.serverUP = serverUP;
    }


    private static void setDriver(ThreadLocal<WebDriver> driver) {
        Hooks.driver = driver;
    }

    public static ThreadLocal<WebDriver> getDriver() {
        return driver;
    }


    public static void embedScreenshot(Scenario scenario) {
        try {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "embedScreenShot");
        } catch (WebDriverException | NullPointerException e) {
            System.out.println("Failed to take embed Screenshot");
        }
    }


    @Before(order = 1)
    public void beforeAll(Scenario scenario) throws IOException, InterruptedException {

        if (!scenario.getId().contains(" ")) {
            if (config.getBrowser().equalsIgnoreCase("Chrome")) {
                setDriver(factory.createRemoteThreadChromeDriver());
            } else if (config.getBrowser().equalsIgnoreCase("FireFox")) {
                setDriver(factory.createRemoteThreadFireFoxDriver());
            }
            if (scenario.getSourceTagNames().contains("@Client")) {
                System.out.println("config.getClient_url() :" + config.getUrl()) ;
                driver.get().get(config.getUrl());
                driver.get().manage().window().maximize();
            }
        }
    }




    @After
    public void afterAll(Scenario scenario) throws IOException, InterruptedException {
        try {
            if (scenario.isFailed()) embedScreenshot(scenario);
        } catch (Exception e) {
            System.out.println("Failed to take screenshot");
        }

        if (driver.get() != null) {
            driver.get().quit();
        }
    }
}
