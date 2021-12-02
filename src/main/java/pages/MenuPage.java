package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.HashMap;
import java.util.List;

public class MenuPage extends BasePage {

    @FindBy(xpath = "//a[@href]//div[text()]/..")
    List<WebElement> products;

    @FindBy(xpath = "//span[contains(text(),'Доставка')]")
    WebElement deliveryBtn;


    public ProductPage selectDish(String name){
        WebDriver driver = WebDriverManager.getDriver();
        for (WebElement element : products){
            if (element.getText().contains(name)){
                saveDish(element, name);
                new WebDriverWait(driver, 10)
                        .ignoring(WebDriverException.class)
                        .until(d -> {
                            ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(false)",
                                    element.findElement(By.xpath(".//*[text()='" + name + "']/..//button")));
                            element.findElement(By.xpath(".//*[text()='" + name + "']/..")).click();
                            return true;
                        });
                break;
            }
        }
        return new ProductPage();
    }

    private void saveDish(WebElement element, String name){
        Integer price = Integer.valueOf(element.findElement(By.xpath("./..//span[1]")).getText());
        if (cart.containsKey(name)) {
            cart.put(name, cart.get(name) + price);
        } else {
            cart.put(name, price);
        }
    }


}
