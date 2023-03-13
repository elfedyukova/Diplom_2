package ru.praktikum.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class UpdateUserInfoTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    protected final UserGenerator generator = new UserGenerator();

    @Test
    @DisplayName("Изменение данных пользователя без авторизации")
    public void UpdateUserUnauthorizedTest() {
        var user = generator.existUser();
        ValidatableResponse creationResponse = client.updateUser(user);
        check.updateUserFailed(creationResponse);
    }

    @Test
    @DisplayName("Изменение данных пользователя с истекшим сроком токена")
    public void UpdateUserInfoExpiredTokenTest() {
        var user = generator.existUser();
        ValidatableResponse creationResponse = client.updateUserExpiredToken(user);
        check.updateUserExpiredToken(creationResponse);
    }

    @Test
    @DisplayName("Изменение данных пользователя с невалидным токеном")
    public void UpdateUserInfoInvalidTokenTest() {
        var user = generator.existUser();
        ValidatableResponse creationResponse = client.updateUserInvalidToken(user);
        check.updateUserInvalidToken(creationResponse);
    }

    @Test
    @DisplayName("Изменение данных пользователя с авторизацией")
    public void UpdateUserAuthorizedTest() {
        var user = generator.existUser();
        ValidatableResponse creationResponses = client.updateUserWithToken(user);
        check.updateUserSuccessed(creationResponses);
    }
}