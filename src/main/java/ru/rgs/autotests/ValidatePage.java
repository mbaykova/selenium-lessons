package ru.rgs.autotests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;

public class ValidatePage extends BasePage{

    @FindBy(xpath = "//*[contains(text(),'Количество путешественников:')]/../div[2]")
    WebElement travellersNumber;

    @FindBy(xpath = "//*[contains(text(),'Дата рождения путешественника:')]/../div[2]")
    WebElement dateBirth;

    @FindBy(xpath = "//*[contains(text(),'Тип поездки:')]/../div[2]")
    WebElement travellersType;

    @FindBy(xpath = "//*[contains(text(),'Сумма страховой защиты:')]/../div[2]")
    WebElement amount;

    @FindBy(xpath = "//*[contains(text(),'Даты поездки:')]/../div[2]")
    WebElement dates;

    @FindBy(xpath = "//*[contains(text(),'Защита при активном отдыхе:')]/../div[2]")
    WebElement activeNeed;

    @FindBy(xpath = "//*[contains(text(),'Территория покрытия:')]/../div[2]")
    WebElement location;


    public ValidatePage(WebDriver driver) {
        super(driver);
    }

    public void validateInsurance(HashMap<String, String> checkData){
        Assert.assertEquals(travellersNumber.getText(), checkData.get("Количество путешественников"));
        Assert.assertEquals(dateBirth.getText(), checkData.get("Дата рождения путешественника"));
        Assert.assertEquals(travellersType.getText(), checkData.get("Тип поездки"));
        Assert.assertEquals(amount.getText(), checkData.get("Сумма страховой защиты"));
        Assert.assertEquals(dates.getText(), checkData.get("Даты поездки"));
        Assert.assertEquals(activeNeed.getText(), checkData.get("Защита при активном отдыхе"));
        Assert.assertEquals(location.getText(), checkData.get("Территория покрытия"));


    }


}
