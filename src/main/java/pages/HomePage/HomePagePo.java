package pages.HomePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.Commons.CommonPo;

public class HomePagePo extends CommonPo {

    @FindAll({
            @FindBy(how = How.XPATH, using = "//ul[@id='element']//span[contains(text(),'Meat, Poultry & Seafood')]")
    })
    public WebElement MeatPoultrySeafood_CategoriesMenu;
    @FindAll({
            @FindBy(how = How.XPATH, using = "//span[contains(text(),'Lamb & Veal')]")
    })
    public WebElement LambVeal;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//ul[@id='element']//span[contains(text(),'Gourmet Ramadan')]")
    })
    public WebElement GourmetRamadan_CategoriesMenu;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//span[contains(text(),'Suhoor')]")
    })
    public WebElement Suhoor;
}
