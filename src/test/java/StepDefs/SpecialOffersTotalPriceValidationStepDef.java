package StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage.HomePageAbstract;
import pages.HomePage.HomePageLogic;
import pages.SpecialOffersTotalPriceValidation.SpecialOffersTotalPriceValidationAbstract;
import pages.SpecialOffersTotalPriceValidation.SpecialOffersTotalPriceValidationLogic;

public class SpecialOffersTotalPriceValidationStepDef {
    HomePageAbstract homePageAbstract = new HomePageLogic();
    SpecialOffersTotalPriceValidationAbstract spabstarct = new SpecialOffersTotalPriceValidationLogic();
    @Given("Open Gourmet And Select Lamb&Veal From Side Categories Menu")
    public void openGourmetAndSelectLambVealFromSideCategoriesMenu() {
        homePageAbstract.selectMeatPoultryAndSeafood();
        homePageAbstract.selectLambVeal();
    }

    @Given("Open Gourmet And Select Suhoor From Side Categories Menu")
    public void openGourmetAndSelectSuhoorFromSideCategoriesMenu() {
        homePageAbstract.selectGourmetRamadan();
        homePageAbstract.selectSuhoor();
    }

    @And("Select All Special Offer Products")
    public void selectAllSpecialOfferProducts()  {
        spabstarct.selectSpecialOfferProducts();
    }


    @Then("Assert That Total Price In Cart Matches The Total Price of Special Products")
    public void assertThatTotalPriceInCartMatchesTheTotalPriceOfSpecialProducts() {
        spabstarct.validateTotalPriceOfSpecialProducts();
    }
}
