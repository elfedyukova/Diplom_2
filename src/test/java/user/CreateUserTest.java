package user;


import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class CreateUserTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    protected final UserGenerator generator = new UserGenerator();

    @Test
    @Description("Создание уникального пользователя")
    public void CreateUserTest() {
        var user = generator.randomUser();
        ValidatableResponse creationResponse = client.createUser(user);
        check.createdUserSuccess(creationResponse);
    }

    @Test
    @Description("Создание пользователя, который уже зарегистрирован")
    public void CreateExistsUserFailedTest() {
        var user = generator.existUser();
        ValidatableResponse creationResponse = client.createUser(user);
        check.createdUserExistFailed(creationResponse);
    }

    @Test
    @Description("Создание пользователя с пустым email")
    public void CreateUserEmptyEmailFailedTest() {
        var user = generator.emptyEmail();
        ValidatableResponse creationResponse = client.createUser(user);
        check.createdUserEmptyFailed(creationResponse);
    }

    @Test
    @Description("Создание пользователя с пустым password")
    public void CreateUserEmptyPasswordFailedTest() {
        var user = generator.emptyPassword();
        ValidatableResponse creationResponse = client.createUser(user);
        check.createdUserEmptyFailed(creationResponse);
    }

    @Test
    @Description("Создание пользователя с пустым name")
    public void CreateUserEmptyNameFailedTest() {
        var user = generator.emptyName();
        ValidatableResponse creationResponse = client.createUser(user);
        check.createdUserEmptyFailed(creationResponse);
    }

    @Test
    @Description("Создание пользователя без email")
    public void CreateUserNullEmailTest() {
        var user = generator.randomUser();
        user.setEmail(null);
        ValidatableResponse creationResponse = client.createUser(user);
        check.createdUserEmptyFailed(creationResponse);
    }

    @Test
    @Description("Создание пользователя без password")
    public void CreateUserNullPasswordTest() {
        var user = generator.randomUser();
        user.setPassword(null);
        ValidatableResponse creationResponse = client.createUser(user);
        check.createdUserEmptyFailed(creationResponse);
    }

    @Test
    @Description("Создание пользователя без password")
    public void CreateUserNullNameTest() {
        var user = generator.randomUser();
        user.setName(null);
        ValidatableResponse creationResponse = client.createUser(user);
        check.createdUserEmptyFailed(creationResponse);
    }

}
