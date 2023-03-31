package pages.SpecialOffersTotalPriceValidation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.Commons.CommonPo;

import java.util.ArrayList;

public class SpecialOffersTotalPriceValidationPO extends CommonPo {
//    @FindAll({
//            @FindBy(how = How.XPATH, using = "//li[@class='item product product-item']")
//    })
//    public WebElement[] allProducts;


    @FindAll({
            @FindBy(how = How.XPATH, using = "//div[@class='price-box price-final_price']//span[@class='special-price']")
    })
    public WebElement specialOfferProducts;


    @FindAll({
            @FindBy(how = How.XPATH, using = "//li[@class='item product product-item']//div[@class='product details product-item-details']//div[@class='qty-selector-instock']")
    })
    public WebElement inStockProducts;

    @FindAll({
            @FindBy(how = How.XPATH, using = "//li[@class='item product product-item']//a")
    })
    public WebElement productsHREF;


    @FindAll({
            @FindBy(how = How.XPATH, using = "//span[@class='price sub-minicart-price']//span[@class='currency-price']")
    })
    public WebElement cartPrice;

}
