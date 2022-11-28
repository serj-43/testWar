package data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
        private String name;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("testme", "123", "Клиент");
    }

    public static AuthInfo getWrongAuthInfoLog() {
        return new AuthInfo("test", "123", "Клиент");
    }

    public static AuthInfo getWrongAuthInfoPass() {
        return new AuthInfo("testme", "1234", "Клиент");
    }

    @Value
    public static class ClientMail {
        private String email;
    }

    public static ClientMail getClientMail(AuthInfo info) {
        return new ClientMail("noreply@tradesoft.ru");
    }

    @Value
    public static class ClientPhone {
        private String phone;
    }

    public static ClientPhone getClientPhone(AuthInfo info) {
        return new ClientPhone("+8(797)897-97-98");
    }
}
