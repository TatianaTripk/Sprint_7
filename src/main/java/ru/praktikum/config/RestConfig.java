package ru.praktikum.config;

public class RestConfig {
    public static final String HOST = "http://qa-scooter.praktikum-services.ru/";
    public static final String COURIER_CREATE_HANDLER = "/api/v1/courier";
    public static final String COURIER_DELETE_HANDLER = "/api/v1/courier/{id}";
    public static final String COURIER_LOGIN_HANDLER = "/api/v1/courier/login";
    public static final String ORDER_HANDLER = "/api/v1/orders";
    public static final String ORDER_CANCEL_HANDLER = "/api/v1/orders/cancel";

    public static class Colours {
        public static final String GREY = "GREY";
        public static final String BLACK = "BLACK";
    }

    public static class Fields {
        public static final String MESSAGE = "message";
        public static final String ID = "id";
    }

    public static class ErrorMessages {
        public static final String NOT_ENOUGH_DATA_FOR_SIGNUP = "Недостаточно данных для создания учетной записи";
        public static final String LOGIN_ALREADY_USED = "Этот логин уже используется";
        public static final String NOT_ENOUGH_DATA_FOR_LOGIN = "Недостаточно данных для входа";
        public static final String ACCOUNT_NOT_FOUND = "Учетная запись не найдена";
    }
}
