package pages.SpecialOffersTotalPriceValidation;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

public class SpecialOffersTotalPriceValidationLogic extends SpecialOffersTotalPriceValidationAbstract {
    String expectedPrice ;
    String actualPrice   ;
    @Override
    public void selectSpecialOfferProducts()  {
        WebElement allItems = driver.get().findElement(By.xpath("//ol[@class='products list items product-items']"));
        List<WebElement> productItems = allItems.findElements(By.xpath("//li[@class='item product product-item']"));
        System.out.println("array size :" + productItems.size());
        double price = 0.0  ;
        for(int i = 0 ; i < productItems.size() ; i++){
            String name = productItems.get(i).findElements(By.xpath("//div[@class='product-item-actions']//span")).get(1).getAttribute("class");
            System.out.println("name :" + name);
            if(name.equalsIgnoreCase("special-price")){
                price = Double.parseDouble(productItems.get(i).findElement(By.xpath("//div[@class='product-item-actions']//span")).getText());
                String hrefs = productItems.get(i).findElement(By.xpath("//a[@class='product-item-link']")).getAttribute("href");
                System.out.println("Product Link Is :" + hrefs);
                productItems.get(i).findElement(By.xpath("//div[@class='qty-selector-instock']")).click();
                price =+ price ;
            }

        }
        expectedPrice = Double.toString(price);
        actualPrice   = cartPrice.getText();
    }

    @Override
    public void validateTotalPriceOfSpecialProducts() {
        Assert.assertEquals("The Cart Total Price Not Matching The Total Price Of Selected Items",expectedPrice,actualPrice);
    }


}
