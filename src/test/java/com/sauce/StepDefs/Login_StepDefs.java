package com.sauce.StepDefs;

import com.sauce.pages.CheckoutPage;
import com.sauce.pages.LoginPage;
import com.sauce.pages.ProductsPage;
import com.sauce.utilities.ConfigurationReader;
import com.sauce.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Select;

public class Login_StepDefs {

    LoginPage loginPage=new LoginPage();
    ProductsPage productsPage=new ProductsPage();
    CheckoutPage checkoutPage=new CheckoutPage();
    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {

        Driver.get().get(ConfigurationReader.get("url"));

    }
    @When("The user is enters {string} and {string}")
    public void the_user_is_enters_and(String username, String password) {

        loginPage.login(username,password);
    }
    @Then("The user should be able to Login and See {string} header")
    public void the_user_should_be_able_to_login_and_see_header(String expectedText) {

        Assert.assertEquals("verify that header is",expectedText,productsPage.productsText.getText());

    }

    @And("The user should be able to sort products {string}")
    public void theUserShouldBeAbleToSortProducts(String  sorttype) {

        Select select=new Select(productsPage.sortBtn);
        select.selectByVisibleText(sorttype);
    }

    @And("The user should clicks cheapest as {string} and second costline as {string} product")
    public void theUserShouldClicksCheapestAsAndSecondCostlineAsProduct(String cheapest, String secondProduct) {

        productsPage.addProducts(cheapest);
        productsPage.addProducts(secondProduct);

    }

    @And("The user open basket")
    public void theUserOpenBasket() {
        productsPage.basketBtn.click();

    }

    @And("The user clicks checkout")
    public void theUserClicksCheckout() {
        checkoutPage.checkoutBtn.click();
    }

    @And("The user enters detailes {string} {string} {string} and finish the purchase")
    public void theUserEntersDetailesAndFinishThePurchase(String firstname, String lastname, String postcode) {

        checkoutPage.enterDetails(firstname,lastname,postcode);


    }

    @Then("The user verify that items are {string} and able to total is {string}")
    public void theUserVerifyThatItemsAreAndAbleToTotalIs(String totalPrice, String expectedTotal) {

        Assert.assertEquals(expectedTotal,checkoutPage.getTotalPrice(totalPrice));
    }

}
