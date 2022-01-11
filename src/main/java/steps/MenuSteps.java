package steps;

import cucumber.api.java.ru.Дано;
import pages.MenuPage;

public class MenuSteps {

    MenuPage menuPage = new MenuPage();

    @Дано("^выбрано блюдо \"([^\"]*)\"$")
    public void existProductWithName(String name) {
        menuPage.selectDish(name);
    }
}
