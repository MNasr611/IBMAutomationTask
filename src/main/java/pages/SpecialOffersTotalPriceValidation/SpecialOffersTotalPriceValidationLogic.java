package pages.SpecialOffersTotalPriceValidation;

import io.cucumber.java.eo.Do;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

import static utils.WebUtils.implicitWait;
import static utils.WebUtils.scrollElementIntoView;

public class SpecialOffersTotalPriceValidationLogic extends SpecialOffersTotalPriceValidationAbstract {
    Double expectedPrice;
    Double actualPrice;

    @Override
    public void selectSpecialOfferProducts()  {
//        WebElement allItems = driver.get().findElement(By.xpath("//ol[@class='products list items product-items']"));
        WebDriverWait wait = new WebDriverWait(driver.get(), 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[1]/parent::*/parent::*/parent::*//div//div//div[@class='qty-selector-instock']")));
        driver.get().findElement(By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[1]/parent::*/parent::*/parent::*//div//div//div[@class='qty-selector-instock']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Add Location')]")));
        driver.get().findElement(By.xpath("//button[@id='address_details_deliver']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[1]/parent::*/parent::*/parent::*//div//div//div[@class='qty-selector-instock']")));
        List<WebElement> productItems = driver.get().findElements(By.xpath("//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price']"));
        System.out.println("array size :" + productItems.size());
        double finalPrice = 0.00;
        boolean instock ;


        for (int i = 1; i <= productItems.size(); i++) {
            instock = true;
            WebElement price = driver.get().findElement((By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[" + i + "]")));
            scrollElementIntoView(driver.get(), price);
            String href = driver.get().findElement(By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[" + i + "]/parent::*/parent::*//strong//a")).getAttribute("href");
            System.out.println("href :" + href);
            try {
                driver.get().findElement(By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[" + i + "]/parent::*/parent::*/parent::*//div//div//div[@class='qty-selector-instock']")).click();
            } catch (Exception e) {
                instock = false;
            }
            if(instock==true) {
                finalPrice = finalPrice + Double.parseDouble(price.getText().substring(3));
            }
        }
        expectedPrice = finalPrice;
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@src='https://gourmetegypt.com/static/version1679625265/base/Magento/base/default/images/loader-1.gif']")));
        implicitWait(driver.get(), 20);
        String shopCartPrice = cartPrice.getText();
        String modifiedShopCartPrice =    shopCartPrice.replace(",","");
        actualPrice = Double.parseDouble(modifiedShopCartPrice);



    }

    @Override
    public void validateTotalPriceOfSpecialProducts() {
        Assert.assertEquals("The Cart Total Price Not Matching The Total Price Of Selected Items", expectedPrice, actualPrice);
    }

}

