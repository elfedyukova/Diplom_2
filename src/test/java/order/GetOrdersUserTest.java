package order;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class GetOrdersUserTest {

    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    @Description("Получение заказов - неавторизованный пользователь")
    public void GetOrdersUnauthorizedUserTest() {
        ValidatableResponse creationResponse = client.getOrdersUserWithoutToken();
        check.getOrderFailed(creationResponse);
    }

    @Test
    @Description("Получение заказов - истекший токен")
    public void GetOrdersAuthorizedUserExpiredTokenTest() {
        ValidatableResponse creationResponse = client.getOrdersUserExpiredToken();
        check.getOrderFailedExpiredToken(creationResponse);
    }

    @Test
    @Description("Получение заказов - авторизованный пользователь")
    public void GetOrdersAuthorizedUserTest() {
        ValidatableResponse creationResponse = client.getOrdersUser();
        check.getOrderSuccessed(creationResponse);
    }

}
