package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

public class BasePage {

    static HashMap<String, Integer> cart = new HashMap<>();

    public BasePage(){
        PageFactory.initElements(WebDriverManager.getDriver(), this);
    }

    @Step("поле {0} заполняется значением {1}")
    public void fillField(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }
}
