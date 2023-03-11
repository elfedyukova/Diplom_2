package order;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class CreateOrderTest {

    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();
    protected final OrderGenerator generator = new OrderGenerator();

    @Test
    @Description("Создание заказа с авторизацией")
    public void CreateOrdersAuthorizedTest() {
        var order = generator.passOrder();
        ValidatableResponse creationResponse = client.createOrderWithToken(order);
        check.createdOrderSuccessed(creationResponse);
    }

    @Test
    @Description("Создание заказа без авторизации")
    public void CreateOrdersUnauthorizedTest() {
        var order = generator.passOrder();
        ValidatableResponse creationResponse = client.createOrder(order);
        check.createdOrderSuccessed(creationResponse);
    }

    @Test
    @Description("Создание заказа с неверным хешем ингредиентов")
    public void CreateOrdersInvalidHashTest() {
        var order = generator.invalidOrder();
        ValidatableResponse creationResponse = client.createOrder(order);
        check.createdOrderInvalidFailed(creationResponse);
    }

    @Test
    @Description("Проверка ответа 400 Bad Request - пустые ингридиенты")
    public void CreateOrdersEmptyIngredientsTest() {
        var order = generator.empty();
        ValidatableResponse creationResponse = client.createOrder(order);
        check.createdOrderEmptyFailed(creationResponse);
    }

    @Test
    @Description("Проверка ответа 400 Bad Request - без ингридиентов")
    public void CreateOrdersWithoutIngredientsTest() {
        var order = generator.passOrder();
        order.setIngredients(null);
        ValidatableResponse creationResponse = client.createOrder(order);
        check.createdOrderEmptyFailed(creationResponse);
    }

}
