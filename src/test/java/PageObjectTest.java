import org.junit.Test;
import pages.MenuPage;

import java.util.HashMap;

public class PageObjectTest extends BaseTest {

    MenuPage menuPage = new MenuPage();

    @Test
    public void test(){
        HashMap<String, String> fields = new HashMap<>();
        fields.put("Улица и дом", "Россия, Москва, Тверская улица, 1");
        fields.put("Квартира", "1");
        fields.put("Подъезд", "1");
        fields.put("Этаж", "1");
        fields.put("Комментарий", "test");

        menuPage.selectDish("Шефбургер Де Люкс")
                .addDishToCart("Шефбургер Де Люкс")
                .selectDelivery()
                .selectDish("Песто бургер")
                .addDishToCart("Песто бургер")
                .goToCart()
                .checkTitle()
                .existProductWithName("Шефбургер Де Люкс")
                .existProductWithName("Песто бургер")
                .checkAmount()
                .confirm()
                .fillForm(fields)
                .paymentNotAvailable();

    }
}
