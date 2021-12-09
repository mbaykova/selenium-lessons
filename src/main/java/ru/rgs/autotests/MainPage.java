package ru.rgs.autotests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class,'item text--second')]//*[contains(text(),'Путешествия')]")
    WebElement travelsBtn;


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public TravelsPage selectTravels(){
        travelsBtn.click();
        return new TravelsPage(driver);
    }
}
