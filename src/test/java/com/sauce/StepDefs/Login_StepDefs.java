package com.sauce.StepDefs;

import com.sauce.pages.LoginPage;
import com.sauce.pages.ProductsPage;
import com.sauce.utilities.ConfigurationReader;
import com.sauce.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Login_StepDefs {

    LoginPage loginPage=new LoginPage();
    ProductsPage productsPage=new ProductsPage();

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

        Assert.assertEquals("verify that header is",expectedText,productsPage.productsTest.getText());

    }


}
