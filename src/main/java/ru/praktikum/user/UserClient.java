package ru.praktikum.user;

import io.restassured.response.ValidatableResponse;
import ru.praktikum.common.Client;

public class UserClient extends Client {

    protected final String ROOT = "/auth";
    protected final String ROOTUSER = ROOT + "/user";
    protected final String EXPIREDTOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0MDM5MmMzOWVkMjgwMDAxYjJlNjVlZiIsImlhdCI6MTY3Nzk1NTc3OSwiZXhwIjoxNjc3OTU2OTc5fQ.XirfCSJAA8l8IsqhkeGdQ7HyYnzOfM5YKR18HHzY1gI";
    protected final String INVALIDTOKEN = "eyJhbGciOiJIUIsqhkeGdQ7HyYnzOfM5YKR18HHzY1gI";

    public ValidatableResponse createUser(User user) {
        return spec()
                .body(user)
                .when()
                .post(ROOT + "/register")
                .then().log().all();
    }

    public ValidatableResponse loginUser(User user) {
        return spec()
                .body(user)
                .when()
                .post(ROOT + "/login")
                .then().log().all();
    }

    public ValidatableResponse updateUser(User user) {
        return spec()
                .body(user)
                .when()
                .patch(ROOTUSER)
                .then().log().all();
    }

    public ValidatableResponse updateUserExpiredToken(User user) {
        return spec()
                .body(user)
                .auth().oauth2(EXPIREDTOKEN)
                .when()
                .patch(ROOTUSER)
                .then()
                .log().all();
    }

    public ValidatableResponse updateUserWithToken(User user) {
        return spec()
                .body(user)
                .when()
                .patch(ROOTUSER)
                .then().log().all();
    }

    public ValidatableResponse updateUserInvalidToken(User user) {
        return spec()
                .body(user)
                .auth().oauth2(INVALIDTOKEN)
                .when()
                .patch(ROOTUSER)
                .then()
                .log().all();
    }
}
