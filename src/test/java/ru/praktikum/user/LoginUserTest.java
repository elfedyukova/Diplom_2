package ru.praktikum.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class LoginUserTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    protected final UserGenerator generator = new UserGenerator();

    @Test
    @DisplayName("Логин под существующим пользователем")
    public void LoginUserTest() {
        var user = generator.existUser();
        ValidatableResponse creationResponse = client.loginUser(user);
        check.loginUserSuccessed(creationResponse);
    }

    @Test
    @DisplayName("Логин под существующим пользователем без имени")
    public void LoginUserEmptyNameTest() {
        var user = generator.existUser();
        user.setName(null);
        ValidatableResponse creationResponse = client.loginUser(user);
        check.loginUserSuccessed(creationResponse);
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    public void LoginUserInvalidPasswordTest() {
        var user = generator.existUserWrongPassword();
        user.setName(null);
        ValidatableResponse creationResponse = client.loginUser(user);
        check.loginUserFailed(creationResponse);
    }

    @Test
    @DisplayName("Логин с неверным логином")
    public void LoginUserInvalidEmailTest() {
        var user = generator.existUserWrongEmail();
        user.setName(null);
        ValidatableResponse creationResponse = client.loginUser(user);
        check.loginUserFailed(creationResponse);
    }

    @Test
    @DisplayName("Логин с неверным именем")
    public void LoginUserInvalidNameTest() {
        var user = generator.existUserWrongName();
        ValidatableResponse creationResponse = client.loginUser(user);
        check.loginUserSuccessed(creationResponse);
    }

    @Test
    @DisplayName("Логин без пароля")
    public void LoginUserEmptyPasswordTest() {
        var user = generator.existUser();
        user.setPassword(null);
        ValidatableResponse creationResponse = client.loginUser(user);
        check.loginUserFailed(creationResponse);
    }

    @Test
    @DisplayName("Логин без email")
    public void LoginUserEmptyEmailTest() {
        var user = generator.existUser();
        user.setEmail(null);
        ValidatableResponse creationResponse = client.loginUser(user);
        check.loginUserFailed(creationResponse);
    }

    @Test
    @DisplayName("Логин под несуществующим пользователем")
    public void LoginNotExistUserTest() {
        var user = generator.randomUser();
        user.setName(null);
        ValidatableResponse creationResponse = client.loginUser(user);
        check.loginUserFailed(creationResponse);
    }

}