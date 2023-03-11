package user;

import common.Client;
import io.restassured.response.ValidatableResponse;

public class UserClient extends Client {

    protected final String ROOT = "/auth";

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
                .patch(ROOT + "/user")
                .then().log().all();
    }

    public ValidatableResponse updateUserExpiredToken(User user) {
        return spec()
                .body(user)
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0MDM5MmMzOWVkMjgwMDAxYjJlNjVlZiIsImlhdCI6MTY3Nzk1NTc3OSwiZXhwIjoxNjc3OTU2OTc5fQ.XirfCSJAA8l8IsqhkeGdQ7HyYnzOfM5YKR18HHzY1gI")
                .when()
                .patch(ROOT + "/user")
                .then()
                .log().all();
    }

    public ValidatableResponse updateUserWithToken(User user) {
        return spec()
                .body(user)
                .when()
                .patch(ROOT + "/user")
                .then().log().all();
    }
}
