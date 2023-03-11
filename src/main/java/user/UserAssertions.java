package user;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class UserAssertions {

    public void createdUserEmptyFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("message", is("Email, password and name are required fields"))
                .body("success", is(false))

        ;
    }

    public void createdUserExistFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("message", is("User already exists"))
                .body("success", is(false))

        ;
    }

    public void createdUserSuccess(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("success", is(true))
                .body("user", notNullValue())

        ;
    }

    public void loginUserFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("email or password are incorrect"))

        ;
    }

    public void loginUserSuccessed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("success", is(true))
                .body("user", notNullValue())

        ;
    }

    public void updateUserFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("You should be authorised"))

        ;
    }

    public void updateUserSuccessed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("success", is(true))
                .body("message", is("You should be authorised"))

        ;
    }

    public void updateUserExpiredToken(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("jwt expired"))

        ;

    }
}
