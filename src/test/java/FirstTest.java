import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FirstTest {


    @Test
    public void test(){
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.kfc.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//button[text()='Соглашаюсь']")).click();
        driver.findElement(By.xpath("//button[text()='Да, все верно']")).click();

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(d -> {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", driver.findElement(By.xpath("//*[text()='Шефбургер Де Люкс']/..//button")));
                    driver.findElement(By.xpath("//*[text()='Шефбургер Де Люкс']/..")).click();
                    return true;
                });

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Шефбургер Де Люкс']")));

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(d -> {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)",   driver.findElement(By.xpath("//*[contains(text(),'В корзину')]")));
                    driver.findElement(By.xpath("//*[contains(text(),'В корзину')]")).click();
            return true;
        });

        driver.findElement(By.xpath("//span[contains(text(),'Доставка')]")).click();
        driver.findElement(By.xpath("//a[contains(@href,'basket')]")).click();

        Assert.assertTrue("Отсутсвует заголвок - У вас отличный вкус!",
                driver.findElements(By.xpath("//*[text()='У вас отличный вкус!']")).size() > 0);
        Assert.assertTrue("В корзине отсутсвует блюдо - Шефбургер Де Люкс",
                driver.findElements(By.xpath("//*[text()='Шефбургер Де Люкс']")).size() > 0);

        driver.findElement(By.xpath("//*[contains(text(),'Оформить')]")).click();
        driver.findElement(By.xpath("//input[@placeholder='Улица и дом*']")).sendKeys("Россия, Москва, Тверская улица, 1");
        driver.findElement(By.xpath("//input[@placeholder='Подъезд*']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@placeholder='Этаж*']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@placeholder='Кв №*']")).sendKeys("1");
        driver.findElement(By.xpath("//textarea[@placeholder='Комментарий к заказу']")).sendKeys("test");

        Assert.assertFalse("Кнопка - Оплатить активна!",
                driver.findElement(By.xpath("//button[contains(text(),'Оплатить')]")).isEnabled());

        driver.close();
    }
}
