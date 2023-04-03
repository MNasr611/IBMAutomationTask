package pages.SpecialOffersTotalPriceValidation;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

import static utils.WebUtils.scrollElementIntoView;

public class SpecialOffersTotalPriceValidationLogic extends SpecialOffersTotalPriceValidationAbstract {
    String expectedPrice;
    String actualPrice;

    @Override
    public void selectSpecialOfferProducts() {
//        WebElement allItems = driver.get().findElement(By.xpath("//ol[@class='products list items product-items']"));
        WebDriverWait wait = new WebDriverWait(driver.get(), 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[1]/parent::*/parent::*/parent::*//div//div//div[@class='qty-selector-instock']")));
        driver.get().findElement(By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[1]/parent::*/parent::*/parent::*//div//div//div[@class='qty-selector-instock']")).click();
        wait = new WebDriverWait(driver.get(), 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Add Location')]")));
        driver.get().findElement(By.xpath("//button[@id='address_details_deliver']")).click();
        wait = new WebDriverWait(driver.get(), 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[1]/parent::*/parent::*/parent::*//div//div//div[@class='qty-selector-instock']")));
        List<WebElement> productItems = driver.get().findElements(By.xpath("//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price']"));
        System.out.println("array size :" + productItems.size());
        double finalPrice = 0.00;
        boolean instock ;
        for (int i = 1; i <= productItems.size()+1; i++) {
            instock = true;
            WebElement price = driver.get().findElement((By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[" + i + "]")));
            scrollElementIntoView(driver.get(), price);
            System.out.println("price :" + price.getText().substring(3));

            String href = driver.get().findElement(By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[" + i + "]/parent::*/parent::*//strong//a")).getAttribute("href");
            System.out.println("href :" + href);
            try {
                driver.get().findElement(By.xpath("(//li[@class='item product product-item']//div[@class='product-item-actions']//span[@class='special-price'])[" + i + "]/parent::*/parent::*/parent::*//div//div//div[@class='qty-selector-instock']")).click();
            } catch (Exception e) {
                instock = false;
            }
            if(instock==true) {
                finalPrice = finalPrice + Double.parseDouble(price.getText().substring(3));
                System.out.println("finalPrice  :" + finalPrice);

            }

        }
        expectedPrice = Double.toString(finalPrice);
        actualPrice = cartPrice.getText();


    }

    @Override
    public void validateTotalPriceOfSpecialProducts() {
        Assert.assertEquals("The Cart Total Price Not Matching The Total Price Of Selected Items", expectedPrice, actualPrice);
    }

}

