package ru.rgs.autotests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.WebDriverManager;

import java.util.HashMap;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void fillField(WebElement element, String value){
    //    element.click();
        element.clear();
        element.sendKeys(value);
    }
}
