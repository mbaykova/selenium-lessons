import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.WebDriverManager;

public class BaseTest {

    protected static WebDriver driver = WebDriverManager.getDriver();

    @Before
    public void beforeMethod(){
        driver.findElement(By.xpath("//button[text()='Соглашаюсь']")).click();
        driver.findElement(By.xpath("//button[text()='Да, все верно']")).click();
    }

    @AfterClass
    public static void afterMethod(){
        driver.quit();
    }
}
