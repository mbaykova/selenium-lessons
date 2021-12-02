package pages;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//*[text()='У вас отличный вкус!']")
    WebElement title;

    @FindBy(xpath = "//*[contains(text(),'Оформить')]")
    WebElement confirmBtn;

    @FindBy(xpath = "//div/a[contains(@href,'product')]")
    List<WebElement> products;

    public CartPage checkTitle() {
        try {
            Assert.assertTrue("Отсутсвует заголвок - У вас отличный вкус!",
                    title.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("Отсутсвует заголвок - У вас отличный вкус!");
        }
        return this;
    }

    public CartPage existProductWithName(String name){
        for (WebElement element : products){
            if (element.getText().contains(name)){
                return this;
            }
        }
        Assert.fail("Отсутсвует продукт с наименованием: " + name);
        return this;
    }

    public DeliveryPage confirm(){
        confirmBtn.click();
        return new DeliveryPage();
    }
}
