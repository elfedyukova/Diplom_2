package user;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class UpdateUserInfoTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    protected final UserGenerator generator = new UserGenerator();


    @Test
    @Description("Изменение данных пользователя без авторизации")
    public void UpdateUserUnauthorizedTest() {
        var user = generator.existUser();
        ValidatableResponse creationResponse = client.updateUser(user);
        check.updateUserFailed(creationResponse);
    }

    @Test
    @Description("Изменение данных пользователя с истекшим сроком токена")
    public void UpdateUserInfoExpiredTokenTest() {
        var user = generator.existUser();
        ValidatableResponse creationResponse = client.updateUserExpiredToken(user);
        check.updateUserExpiredToken(creationResponse);
    }

    @Test
    @Description("Изменение данных пользователя с авторизацией")
    public void UpdateUserAuthorizedTest() {
        var user = generator.existUser();
        ValidatableResponse creationResponses = client.updateUserWithToken(user);
        check.updateUserSuccessed(creationResponses);
    }
}
//Для обеих ситуаций нужно проверить, что любое поле можно изменить. Для неавторизованного пользователя — ещё и то, что система вернёт ошибку.