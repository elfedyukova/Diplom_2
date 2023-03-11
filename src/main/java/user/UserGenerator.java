package user;


import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    public User existUser() {
        return new User("test-data@yandex.ru", "password", "Username");
    }

    public User existUserWrongPassword() {
        return new User("test-data@yandex.ru", "invalid", "Username");
    }

    public User existUserWrongEmail() {
        return new User("invalid@yandex.ru", "password", "Username");
    }

    public User existUserWrongName() {
        return new User("test-data@yandex.ru", "password", "invalid");
    }

    public User randomUser() {
        return new User(RandomStringUtils.random(10) + "@yandex.ru", RandomStringUtils.random(12), RandomStringUtils.random(10));
    }

    public User emptyEmail() {
        return new User("", RandomStringUtils.random(12), RandomStringUtils.random(10));
    }

    public User emptyPassword() {
        return new User(RandomStringUtils.random(10) + "@yandex.ru", "", RandomStringUtils.random(10));
    }

    public User emptyName() {
        return new User(RandomStringUtils.random(10) + "@yandex.ru", RandomStringUtils.random(10), "");
    }
}
