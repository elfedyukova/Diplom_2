package ru.praktikum.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class GetOrdersUserTest {

    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    @DisplayName("Получение заказов - неавторизованный пользователь")
    public void GetOrdersUnauthorizedUserTest() {
        ValidatableResponse creationResponse = client.getOrdersUserWithoutToken();
        check.getOrderFailed(creationResponse);
    }

    @Test
    @DisplayName("Получение заказов - истекший токен")
    public void GetOrdersAuthorizedUserExpiredTokenTest() {
        ValidatableResponse creationResponse = client.getOrdersUserExpiredToken();
        check.getOrderFailedExpiredToken(creationResponse);
    }

    @Test
    @DisplayName("Получение заказов - невалидный токен")
    public void GetOrdersAuthorizedUserInvalidTokenTest() {
        ValidatableResponse creationResponse = client.getOrdersUserInvalidToken();
        check.getOrderFailedInvalidToken(creationResponse);
    }

    @Test
    @DisplayName("Получение заказов - авторизованный пользователь")
    public void GetOrdersAuthorizedUserTest() {
        ValidatableResponse creationResponse = client.getOrdersUser();
        check.getOrderSuccessed(creationResponse);
    }

}
