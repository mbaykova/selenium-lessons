import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.rgs.autotests.MainPage;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class RgsTest {

    private static WebDriver driver;
    HashMap<String, String> data = new HashMap<>();
    HashMap<String, String> dataCheck = new HashMap<>();

    @Before
    public void beforeMethod(){
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.rgs.ru/travels");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test
    public void test(){
        data.put("Дата рождения", "12.12.1992");
        data.put("Страна", "Таиланд");
        data.put("Дата начала", "01.03.2022");
        data.put("Дата окончания", "14.03.2022");

        dataCheck.put("Количество путешественников", "1");
        dataCheck.put("Дата рождения путешественника",  "12.12.1992");
        dataCheck.put("Тип поездки", "Однократная");
        dataCheck.put("Сумма страховой защиты", "100 000 $");
        dataCheck.put("Даты поездки", "01.03.2022 - 14.03.2022 (14 дней в поездке)");
        dataCheck.put("Защита при активном отдыхе", "не нужна");
        dataCheck.put("Территория покрытия", "Таиланд");
        MainPage mainPage = new MainPage(driver);
        mainPage.selectTravels()
                .buyOnline()
                .fillForm(data)
                .buyPremium()
                .validateInsurance(dataCheck);
    }
}
