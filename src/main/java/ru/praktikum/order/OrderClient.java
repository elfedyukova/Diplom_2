package ru.praktikum.order;

import io.restassured.response.ValidatableResponse;
import ru.praktikum.common.Client;

public class OrderClient extends Client {

    protected final String ROOT = "/orders";
    protected final String EXPIREDTOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0MDM5MmMzOWVkMjgwMDAxYjJlNjVlZiIsImlhdCI6MTY3Nzk1NTc3OSwiZXhwIjoxNjc3OTU2OTc5fQ.XirfCSJAA8l8IsqhkeGdQ7HyYnzOfM5YKR18HHzY1gI";
    protected final String INVALIDTOKEN = "eyJhbGciOiJIUIsqhkeGdQ7HyYnzOfM5YKR18HHzY1gI";
    protected final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0MDVlZDkwOWVkMjgwMDAxYjJlNzNiNCIsImlhdCI6MTY3ODExMDA5NiwiZXhwIjoxNjc4MTExMjk2fQ.PQiG0LaYit0WOP5gKInegrZQRBwwBDTd_R_keKUvn4k";

    public ValidatableResponse createOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    public ValidatableResponse createOrderWithToken(Order order) {
        return spec()
                .auth().oauth2(TOKEN)
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    public ValidatableResponse getOrdersUser() {
        return spec()
                .auth().oauth2(TOKEN)
                .when()
                .get(ROOT)
                .then().log().all();
    }

    public ValidatableResponse getOrdersUserExpiredToken() {
        return spec()
                .auth().oauth2(EXPIREDTOKEN)
                .when()
                .get(ROOT)
                .then().log().all();

    }

    public ValidatableResponse getOrdersUserWithoutToken() {
        return spec()
                .when()
                .get(ROOT)
                .then().log().all();
    }

    public ValidatableResponse getOrdersUserInvalidToken() {
        return spec()
                .auth().oauth2(INVALIDTOKEN)
                .when()
                .get(ROOT)
                .then().log().all();

    }
}