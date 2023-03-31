package base;

import core.Hooks;
import org.openqa.selenium.*;
//import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;


public class PageObjectBase {
    public ThreadLocal<WebDriver>  driver;

    public PageObjectBase()  {
        this.driver = Hooks.getDriver();
        PageFactory.initElements(driver.get(), this);
    }



}