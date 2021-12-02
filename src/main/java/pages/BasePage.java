package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

public class BasePage {

    static HashMap<String, Integer> cart = new HashMap<>();

    public BasePage(){
        PageFactory.initElements(WebDriverManager.getDriver(), this);
    }

    public void fillField(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }
}
