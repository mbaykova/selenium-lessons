package steps;

import cucumber.api.java.ru.Дано;
import pages.ProductPage;

public class ProductSteps {

    ProductPage productPage = new ProductPage();

    @Дано("^блюдо \"([^\"]*)\" добавлено в корзину$")
    public void addDishToCart(String name) {
        productPage.addDishToCart(name);
    }

    @Дано("^выбран способ получения заказа - доставка$")
    public void selectDelivery() {
        productPage.selectDelivery();
    }

    @Дано("^выполнен переход в корзину$")
    public void goToCart() {
        productPage.goToCart();
    }
}

