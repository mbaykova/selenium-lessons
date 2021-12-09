package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class DeliveryPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Улица и дом*']")
    WebElement street;

    @FindBy(xpath = "//input[@placeholder='Подъезд*']")
    WebElement entrance;

    @FindBy(xpath = "//input[@placeholder='Этаж*']")
    WebElement floor;

    @FindBy(xpath = "//input[@placeholder='Кв №*']")
    WebElement flat;

    @FindBy(xpath = "//textarea[@placeholder='Комментарий к заказу']")
    WebElement comment;

    @FindBy(xpath = "//button[contains(text(),'Оплатить')]")
    WebElement payBtn;



    public DeliveryPage fillForm(HashMap<String, String> fields){
        for (Map.Entry<String, String> field : fields.entrySet()){
            switch (field.getKey()){
                case "Улица и дом" :
                    fillField(street, field.getValue());
                    break;
                case "Квартира" :
                    fillField(flat, field.getValue());
                    break;
                case "Подъезд" :
                    fillField(entrance, field.getValue());
                    break;
                case "Этаж" :
                    fillField(floor, field.getValue());
                    break;
                case "Комментарий" :
                    fillField(comment, field.getValue());
                    break;
                default:
                    Assert.fail("Не объявлено поле: " + field.getKey());
            }
        }
        return this;
    }


    public DeliveryPage paymentNotAvailable(){
        Assert.assertFalse("Кнопка - Оплатить активна!",
                payBtn.isEnabled());
        return this;
    }





}

