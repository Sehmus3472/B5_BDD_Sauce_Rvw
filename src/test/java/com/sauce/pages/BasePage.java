package com.sauce.pages;

import com.sauce.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

public class BasePage {



    public BasePage() {
        PageFactory.initElements(Driver.get(),this);
    }
}
