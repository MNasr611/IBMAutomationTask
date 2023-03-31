package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public final class WebDriverFactory {
    public static WebDriver createChromeWebDriver() {
        WebDriverManager.chromedriver().reset();
        WebDriverManager.chromedriver().setup();
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type");
        options.addArguments("--no-sandbox");
//        options.addArguments("--headless");
        return new ChromeDriver(options);
    }

    public static RemoteWebDriver createRemoteChromeWebDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "106");
        options.setCapability("platformName", "linux/amd64");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--test-type");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--disable-extensions");
//        options.addArguments("--headless");
//        options.addArguments("--disable-dev-shm-usage");
//        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//        options.setExperimentalOption("useAutomationExtension", false);
        options.setHeadless(true);

//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);



//        WebDriverManager.chromedriver().setup();
//        DesiredCapabilities capabilities = new DesiredCapabilities() ;
//        ChromeOptions options = new ChromeOptions();
//        System.out.println(options.getVersion());
////        WebDriverManager.chromedriver().reset();
//        capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
//        capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
//        capabilities.setCapability (CapabilityType.ACCEPT_INSECURE_CERTS, true);
//        capabilities.setCapability("browserVersion", "latest");
//        capabilities.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
//        options.addArguments("--test-type");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--headless");
//        options.addArguments("--ignore-ssl-errors=yes");
//        options.addArguments("--ignore-certificate-errors");
////        options.addArguments("--disable-dev-shm-usage");
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        WebDriverManager.chromedriver().capabilities(capabilities);

        return new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"),capabilities);
    }


    public static RemoteWebDriver createRemoteChromeWebDriver2() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--ignore-certificate-errors");
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
    }

    public static RemoteWebDriver createRemoteChromeWebDriver3() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "106");
        options.setCapability("platformName", "linux/amd64");
        options.setHeadless(true);
        return new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"),options);
    }

//    public static RemoteWebDriver createRemoteChromeWebDriver4() throws MalformedURLException {
////        DesiredCapabilities cap=DesiredCapabilities.chrome();
////        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
////        cap.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
////        cap.setCapability("version", "106.0");
//////        cap.setCapability(CapabilityType.BROWSER_VERSION,);
////
////        return new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"),cap);
//    }

}



