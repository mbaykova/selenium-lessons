import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class FirstTest extends BaseTest{


    @Test
    public void test() {
        HashMap<String, Integer> cart = new HashMap<>();

        Integer price = Integer.valueOf(driver.findElement(By.xpath("//*[text()='Шефбургер Де Люкс']/..//span[1]")).getText());
        if (cart.containsKey("Шефбургер Де Люкс")) {
            cart.put("Шефбургер Де Люкс", cart.get("Шефбургер Де Люкс") + price);
        } else {
            cart.put("Шефбургер Де Люкс", price);
        }

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(d -> {
                    ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(false)", d.findElement(By.xpath("//*[text()='Шефбургер Де Люкс']/..//button")));
                    d.findElement(By.xpath("//*[text()='Шефбургер Де Люкс']/..")).click();
                    return true;
                });

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Шефбургер Де Люкс']")));

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(d -> {
                    ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(false)", d.findElement(By.xpath("//*[contains(text(),'В корзину')]")));
                    d.findElement(By.xpath("//*[contains(text(),'В корзину')]")).click();
                    return true;
                });

        driver.findElement(By.xpath("//span[contains(text(),'Доставка')]")).click();
        price = Integer.valueOf(driver.findElement(By.xpath("//*[text()='Песто бургер']/..//span[1]")).getText());

        if (cart.containsKey("Песто бургер")) {
            cart.put("Песто бургер", cart.get("Песто бургер") + price);
        } else {
            cart.put("Песто бургер", price);
        }

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(d -> {
                    ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(false)", d.findElement(By.xpath("//*[text()='Песто бургер']/..//button")));
                    d.findElement(By.xpath("//*[text()='Песто бургер']/..")).click();
                    return true;
                });

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Песто бургер']")));

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(d -> {
                    ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(false)", d.findElement(By.xpath("//*[contains(text(),'В корзину')]")));
                    d.findElement(By.xpath("//*[contains(text(),'В корзину')]")).click();
                    return true;
                });


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
        driver.findElement(By.xpath("//input[@placeholder='Улица и дом*']")).sendKeys("Россия, Москва, Тверская улица, 1");
        driver.findElement(By.xpath("//input[@placeholder='Подъезд*']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@placeholder='Этаж*']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@placeholder='Кв №*']")).sendKeys("1");
        driver.findElement(By.xpath("//textarea[@placeholder='Комментарий к заказу']")).sendKeys("test");

        Assert.assertFalse("Кнопка - Оплатить активна!",
                driver.findElement(By.xpath("//button[contains(text(),'Оплатить')]")).isEnabled());

    }
}
