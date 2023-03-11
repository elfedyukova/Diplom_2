package order;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertions {

    public void createdOrderEmptyFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("message", is("Ingredient ids must be provided"))
                .body("success", is(false))

        ;
    }

    public void createdOrderInvalidFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(500)
                .contentType(ContentType.HTML)

        ;
    }

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

    public void getOrderSuccessed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("orders", notNullValue())
                .body("success", is(true))

        ;
    }

    public void getOrderFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("You should be authorised"))

        ;
    }

    public void getOrderFailedExpiredToken(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("success", is(false))
                .body("message", is("jwt expired"))

        ;
    }
}
