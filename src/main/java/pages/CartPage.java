package pages;

import io.qameta.allure.Step;
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

    @FindBy(xpath = "//*[text()='Итого к оплате']/../..//span[@class][1]")
    WebElement commonAmount;

    @Step("Присутвствует заголовок - У вас отличный вкус!")
    public CartPage checkTitle() {
        try {
            Assert.assertTrue("Отсутсвует заголвок - У вас отличный вкус!",
                    title.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("Отсутсвует заголвок - У вас отличный вкус!");
        }
        return this;
    }

    @Step("В корзине присутвствует блюдо - {0}")
    public CartPage existProductWithName(String name){
        for (WebElement element : products){
            if (element.getText().contains(name)){
                return this;
            }
        }
        Assert.fail("Отсутсвует продукт с наименованием: " + name);
        return this;
    }

    @Step("Выполнено подтверждение заказа")
    public DeliveryPage confirm(){
        confirmBtn.click();
        return new DeliveryPage();
    }

    @Step("Итоговое значение суммы в корзине - корректно")
    public CartPage checkAmount(){
        int expectedCommonAmount = 0;
        int actualCommonAmount = Integer.parseInt(commonAmount.getText());
        for (Integer amount : cart.values()){
            expectedCommonAmount = expectedCommonAmount + amount;
        }
        Assert.assertTrue(String.format("Итоговое значение [%s] не равно ожидаемому значению [%s]", actualCommonAmount, expectedCommonAmount),
                actualCommonAmount == expectedCommonAmount + 1);
        return this;
    }
}
