package ru.praktikum.user;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class UserAssertions {

    @Step("Создание пользователя без обязательных полей")
    public void createdUserEmptyFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("message", is("Email, password and name are required fields"))
                .body("success", is(false))

        ;
    }

    @Step("Создание уже существующего пользователя")
    public void createdUserExistFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("message", is("User already exists"))
                .body("success", is(false))

        ;
    }

    @Step("Создание пользователя")
    public void createdUserSuccess(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("success", is(true))
                .body("user", notNullValue())

        ;
    }

    @Step("Авторизация с неверными данными")
    public void loginUserFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("email or password are incorrect"))

        ;
    }

    @Step("Авторизация пользователя")
    public void loginUserSuccessed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("success", is(true))
                .body("user", notNullValue())

        ;
    }

    @Step("Обновление информации о пользователе без авторизации")
    public void updateUserFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("You should be authorised"))

        ;
    }

    @Step("Обновление информации о пользователе")
    public void updateUserSuccessed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("success", is(true))
                .body("message", is("You should be authorised"))

        ;
    }

    @Step("Обновление информации о пользователе с истекшим сроком у токена")
    public void updateUserExpiredToken(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("jwt expired"))

        ;
    }

    @Step("Обновление информации о пользователе с невалидным токеном")
    public void updateUserInvalidToken(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("jwt malformed"))

        ;
    }
}
