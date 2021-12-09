import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.WebDriverManager;

import java.util.HashMap;

public class RefactoringFirstTest extends BaseTest{

    HashMap<String, Integer> cart = new HashMap<>();

    WebDriver driver = WebDriverManager.getDriver();

    @Test
    public void test() {
        selectItem("Шефбургер Де Люкс");
        addItemToCart("Шефбургер Де Люкс");
        driver.findElement(By.xpath("//span[contains(text(),'Доставка')]")).click();

        selectItem("Песто бургер");
        addItemToCart("Песто бургер");

        driver.findElement(By.xpath("//a[contains(@href,'basket')]")).click();

        Assert.assertTrue("Отсутсвует заголвок - У вас отличный вкус!",
                driver.findElements(By.xpath("//*[text()='У вас отличный вкус!']")).size() > 0);
        Assert.assertTrue("В корзине отсутсвует блюдо - Шефбургер Де Люкс",
                driver.findElements(By.xpath("//*[text()='Шефбургер Де Люкс']")).size() > 0);

        int expectedCommonAmount = 0;
        int actualCommonAmount = Integer.parseInt(driver.findElement(By.xpath("//*[text()='Итого к оплате']/../..//span[@class][1]")).getText());
        for (Integer amount : cart.values()){
            expectedCommonAmount = expectedCommonAmount + amount;
        }
        Assert.assertTrue(String.format("Итоговое значение [%s] не равно ожидаемому значению [%s]", actualCommonAmount, expectedCommonAmount),
                actualCommonAmount == expectedCommonAmount);

        driver.findElement(By.xpath("//*[contains(text(),'Оформить')]")).click();
        fillField(By.xpath("//input[@placeholder='Улица и дом*']"),  "Россия, Москва, Тверская улица, 1");
        fillField(By.xpath("//input[@placeholder='Подъезд*']"),  "1");
        fillField(By.xpath("//input[@placeholder='Этаж*']"),  "1");
        fillField(By.xpath("//input[@placeholder='Кв №*']"),  "1");
        fillField(By.xpath("//textarea[@placeholder='Комментарий к заказу']"),  "test");

        Assert.assertFalse("Кнопка - Оплатить активна!",
                driver.findElement(By.xpath("//button[contains(text(),'Оплатить')]")).isEnabled());

    }

    private void selectItem(String name){
        Integer price = Integer.valueOf(driver.findElement(By.xpath("//*[text()='" + name + "']/..//span[1]")).getText());
        if (cart.containsKey(name)) {
            cart.put(name, cart.get(name) + price);
        } else {
            cart.put(name, price);
        }

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(d -> {
                    ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(false)", d.findElement(By.xpath("//*[text()='Шефбургер Де Люкс']/..//button")));
                    d.findElement(By.xpath("//*[text()='" + name + "']/..")).click();
                    return true;
                });

    }

    private void addItemToCart(String name){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='" + name + "']")));

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(d -> {
                    ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(false)", d.findElement(By.xpath("//*[contains(text(),'В корзину')]")));
                    d.findElement(By.xpath("//*[contains(text(),'В корзину')]")).click();
                    return true;
                });
    }

    public void fillField(By locator, String value){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }
}
