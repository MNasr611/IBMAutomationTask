package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.WebDriverFactory;

import java.net.MalformedURLException;
import java.net.URL;


public class DriverFactory {
    public WebDriver driver;
    protected static ThreadLocal<RemoteWebDriver> remoteWebDriverThreadLocal = new ThreadLocal<>();
    protected static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    final private static String dockerURL = "http://localhost:4444/wd/hub";


    public WebDriver createDriver() {
        // run in sequential mode locally
        driver = WebDriverFactory.createChromeWebDriver();
        return driver;
    }

    public ThreadLocal<WebDriver> createThreadDriver() {
        //ThreadLocal for running in parallel mode locally
        WebDriverManager.chromedriver().reset();
        WebDriverManager.chromedriver().setup();
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");

//        options.addArguments("--headless");
        webDriver.set(new ChromeDriver(options));
        return webDriver;
    }

    public WebDriver createRemoteDriver(){
        // for sequential mode exec on remote
        DesiredCapabilities cap = new DesiredCapabilities();
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        //to make the run not affected with limited resources
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("--ignore-ssl-errors=yes");
        chromeOptions.addArguments("--ignore-certificate-errors");
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        // to add the options to capabilities
        driver = WebDriverManager.chromedriver().capabilities(cap).remoteAddress("http://localhost:4444/wd/hub").create();
        // this creates an instance from chromeDriver on remote using the WebDriverManager ( in case no chromeDriver setuped )
        return  driver;
        // assign it to normal webdriver
    }


    public ThreadLocal<WebDriver> createRemoteThreadChromeDriver() throws MalformedURLException {
        // fo parallel exec on remote
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setPlatform(Platform.LINUX);
        cap.setBrowserName("Chrome");
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        //to make the run not affected with limited resources
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("--ignore-ssl-errors=yes");
        chromeOptions.addArguments("--ignore-certificate-errors");
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        // to add the options to capabilities
        webDriver.set(new RemoteWebDriver(new URL(dockerURL),cap));
        // to intialize the thread driver "webdriver" as "RemoteWebDriver" with the new capabilities
        System.out.println("last driver val "+ remoteWebDriverThreadLocal);
        return webDriver ;
    }


    public ThreadLocal<WebDriver> createRemoteThreadFireFoxDriver() throws MalformedURLException {
        // fo parallel exec on remote
        DesiredCapabilities cap = new DesiredCapabilities();
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--disable-dev-shm-usage");
        //to make the run not affected with limited resources
        firefoxOptions.addArguments("--test-type");
        firefoxOptions.addArguments("--ignore-ssl-errors=yes");
        firefoxOptions.addArguments("--ignore-certificate-errors");
        cap.setCapability(ChromeOptions.CAPABILITY, firefoxOptions);
        // to add the options to capabilities
        webDriver.set(new RemoteWebDriver(new URL(dockerURL),cap));
        // to intialize the thread driver "webdriver" as "RemoteWebDriver" with the new capabilities
        System.out.println("last driver val "+ remoteWebDriverThreadLocal);
        return webDriver ;
    }
}
