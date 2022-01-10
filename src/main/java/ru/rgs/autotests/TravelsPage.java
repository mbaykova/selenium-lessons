package ru.rgs.autotests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class TravelsPage extends BasePage {

    @FindBy(xpath = "//*[text()='Купить онлайн']")
    WebElement buyBtn;

    @FindBy(xpath = "//div[@id='RI-product-steps']//input")
    WebElement birtDayInput;

    @FindBy(xpath = "//*[text()='Однократная']")
    WebElement typeTravelOne;

    @FindBy(xpath = "//*[text()='Выбрать страну']")
    WebElement selectCountry;

    @FindBy(xpath = "//*[text()='Доллары']")
    WebElement dollars;

    @FindBy(xpath = "//button[contains(text(), '100')]")
    WebElement dollars100;

    @FindBy(xpath = "//input[@name='content.contract.beginDate']")
    WebElement beginDate;

    @FindBy(xpath = "//input[@name='content.contract.arrival']")
    WebElement endDate;

    @FindBy(xpath = "//button[contains(text(), 'Не нужна')]")
    WebElement notNeed;

    @FindBy(xpath = "(//button[contains(text(), 'Купить')])[1]")
    WebElement buyPremium;

    @FindBy(xpath = "//button[contains(text(), 'Продолжить')]")
    WebElement continueBtn;

    @FindBy(xpath = "//button[contains(text(),'Хорошо')]")
    WebElement btnOk;

    @FindBy(xpath = "//label[text()= 'Выберите программу'][@class='']")
    WebElement chooseProgramm;


    public TravelsPage(WebDriver driver) {
        super(driver);
    }

    public TravelsPage buyOnline() {
        btnOk.click();
        buyBtn.click();
        return this;
    }

    public TravelsPage fillForm(HashMap<String, String> data) {
        driver.switchTo().frame("RESOLUTE_INSURANCE");
        fillField(birtDayInput, data.get("Дата рождения"));
        typeTravelOne.click();
        selectCountry.click();
        selectCountry.findElement(By.xpath("./..//input")).sendKeys(data.get("Страна") + Keys.ENTER);
        dollars.click();
        dollars100.click();
        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(false)",
                dollars100);

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(endDate));
        fillField(beginDate, data.get("Дата начала"));

        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(true)",
                endDate);
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(endDate));

        fillField(endDate, data.get("Дата окончания"));
        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(false)",
                chooseProgramm);

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(notNeed));
        ((JavascriptExecutor) driver).executeScript("return arguments[0].click()",
                notNeed);
        return this;

    }

    public ValidatePage buyPremium() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(notNeed));
        ((JavascriptExecutor) driver).executeScript("return arguments[0].click()",
                buyPremium);
        return new ValidatePage(driver);
    }
}
