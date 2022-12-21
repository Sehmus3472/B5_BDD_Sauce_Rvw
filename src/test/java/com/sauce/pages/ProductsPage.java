package com.sauce.pages;

import com.sauce.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage extends BasePage{
    @FindBy(xpath = "//span[text()='Products']")
    public WebElement productsText;

    @FindBy(css = ".product_sort_container")
    public WebElement sortBtn;

    @FindBy(css = ".shopping_cart_link")
    public WebElement basketBtn;


    public void sortProduct(String sorttype){

        Select select=new Select(sortBtn);
        select.selectByVisibleText(sorttype);

    }

    public void addProducts(String price){

        Driver.get().findElement(By.xpath("//*[text()='"+price+"']/../button")).click();

    }


}
