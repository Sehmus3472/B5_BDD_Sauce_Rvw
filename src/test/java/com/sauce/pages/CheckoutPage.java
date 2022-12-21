package com.sauce.pages;

import com.sauce.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{

    @FindBy(id = "checkout")
    public WebElement checkoutBtn;

    @FindBy(id = "first-name")
    public WebElement inputFirstName;

    @FindBy(id = "last-name")
    public WebElement inputLastName;

    @FindBy(id = "postal-code")
    public WebElement inputPostCode;

    @FindBy(id = "continue")
    public WebElement continueBtn;


    public void enterDetails(String firstname,String lastname,String postcode){

        inputFirstName.sendKeys(firstname);
        inputLastName.sendKeys(lastname);
        inputPostCode.sendKeys(postcode);
        continueBtn.click();

    }

    public String getTotalPrice(String totalPrice){

        return Driver.get().findElement(By.xpath("//*[text()='"+totalPrice+"']")).getText();

    }

}
