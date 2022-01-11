package steps;

import cucumber.api.java.ru.Дано;
import pages.CartPage;

public class CartSteps {

    CartPage cartPage = new CartPage();

    @Дано("^Присутвствует заголовок - У вас отличный вкус!$")
    public void checkTitle() {
        cartPage.checkTitle();
    }

    @Дано("^В корзине присутвствует блюдо \"([^\"]*)\"$")
    public void existProductWithName(String name) {
        cartPage.existProductWithName(name);
    }

    @Дано("^Выполнено подтверждение заказа$")
    public void confirm() {
        cartPage.confirm();
    }

    @Дано("^Итоговое значение суммы в корзине - корректно$")
    public void checkAmount() {
        cartPage.checkAmount();
    }
}
