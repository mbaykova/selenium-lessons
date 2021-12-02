package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//h1[text()]")
    WebElement title;

    @FindBy(xpath = "//*[contains(text(),'В корзину')]")
    WebElement addToCart;

    @FindBy(xpath = "//a[contains(@href,'basket')]")
    WebElement goToCart;

    @FindBy(xpath = "//span[contains(text(),'Доставка')]")
    WebElement selectDelivery;


    public ProductPage addDishToCart(String name) {
        WebDriver driver = WebDriverManager.getDriver();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElement(title, name));

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(d -> {
                    ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(false)", addToCart);
                    addToCart.click();
                    return true;
                });
        return this;
    }

    public MenuPage selectDelivery(){
        selectDelivery.click();
        return new MenuPage();
    }

    public CartPage goToCart(){
        goToCart.click();
        return new CartPage();
    }
}
