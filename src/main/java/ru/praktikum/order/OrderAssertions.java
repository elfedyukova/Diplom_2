package ru.praktikum.order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertions {

    @Step("Создание заказа без ингредиентов")
    public void createdOrderEmptyFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("message", is("Ingredient ids must be provided"))
                .body("success", is(false))

        ;
    }

    @Step("Создание заказа с неверным хешем ингредиентов")
    public void createdOrderInvalidFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(500)
                .contentType(ContentType.HTML)

        ;
    }

    @Step("Создание заказа")
    public void createdOrderSuccessed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("order", notNullValue())
                .body("success", is(true))
                .extract()
                .path("orders")

        ;
    }

    @Step("Получение информации по заказу")
    public void getOrderSuccessed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("orders", notNullValue())
                .body("success", is(true))

        ;
    }

    @Step("Получение информации по заказу без авторизации")
    public void getOrderFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("You should be authorised"))

        ;
    }

    @Step("Получение информации по заказу с просроченным токеном")
    public void getOrderFailedExpiredToken(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("jwt expired"))

        ;
    }

    @Step("Получение информации по заказу с невалидным токеном")
    public void getOrderFailedInvalidToken(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("jwt malformed"))

        ;
    }
}
