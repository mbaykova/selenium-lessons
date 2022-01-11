package steps;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.WebDriverManager;

public class HookSteps {

    protected static WebDriver driver = WebDriverManager.getDriver();


    @Before
    public void beforeMethod(){
        driver.findElement(By.xpath("//button[text()='Соглашаюсь']")).click();
        driver.findElement(By.xpath("//button[text()='Да, все верно']")).click();
    }

    @After
    public void afterMethod(Scenario scenario){
        if (scenario.isFailed()){
            saveScreenshot();
        }
        driver.quit();
    }

    @Attachment(value = "Скриншот при ошибке", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
