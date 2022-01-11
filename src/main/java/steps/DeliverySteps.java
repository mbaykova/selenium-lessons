package steps;

import cucumber.api.java.ru.Дано;
import io.cucumber.datatable.DataTable;
import pages.DeliveryPage;

import java.util.HashMap;
import java.util.Map;

public class DeliverySteps {

    DeliveryPage deliveryPage = new DeliveryPage();

    @Дано("^форма заполняется значениями:$")
    public void fillForm(DataTable dataTable) {
        Map<String, String> params = dataTable.asMap(String.class, String.class);
        deliveryPage.fillForm(params);
    }

    @Дано("^Кнопка 'Оплатить' не активна$")
    public void paymentNotAvailable() {
        deliveryPage.paymentNotAvailable();
    }
}





