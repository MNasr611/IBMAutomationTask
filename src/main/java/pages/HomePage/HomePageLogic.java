package pages.HomePage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebUtils;

import java.time.Duration;


public class HomePageLogic extends HomePageAbstract{
    @Override
    public void selectMeatPoultryAndSeafood() {
        MeatPoultrySeafood_CategoriesMenu.click();
    }

    @Override
    public void selectLambVeal() {
        WebUtils.scrollElementIntoView(driver.get(),LambVeal );
        LambVeal.click();
    }

    @Override
    public void selectGourmetRamadan() {
        GourmetRamadan_CategoriesMenu.click();

    }

    @Override
    public void selectSuhoor() {
        WebUtils.scrollElementIntoView(driver.get(),Suhoor);
        Suhoor.click();
        new WebDriverWait(driver.get() ,Duration.ofSeconds(20)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

    }
}
