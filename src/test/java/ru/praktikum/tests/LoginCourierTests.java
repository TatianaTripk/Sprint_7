package ru.praktikum.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.model.Courier;
import ru.praktikum.steps.CourierSteps;

import static ru.praktikum.config.RestConfig.ErrorMessages.ACCOUNT_NOT_FOUND;
import static ru.praktikum.config.RestConfig.ErrorMessages.NOT_ENOUGH_DATA_FOR_LOGIN;
import static ru.praktikum.config.RestConfig.Fields.ID;
import static ru.praktikum.config.RestConfig.Fields.MESSAGE;

public class LoginCourierTests extends BaseTest {
    private CourierSteps courierSteps = new CourierSteps();
    private Courier courier;

    @Before
    public void setUp() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    @Test
    public void shouldLoginCourierTest() {
        courierSteps.createCourier(courier);
        courierSteps
                .loginCourier(courier)
                .statusCode(200)
                .body(ID, Matchers.notNullValue());
    }
    @Test
    public void shouldNotLoginCourierWithoutLoginTest() {
        courierSteps.createCourier(courier);
        Courier noLoginCourier = new Courier()
                .setLogin(null)
                .setPassword(courier.getPassword());

        courierSteps
                .loginCourier(noLoginCourier)
                .statusCode(400)
                .body(MESSAGE, Matchers.is(NOT_ENOUGH_DATA_FOR_LOGIN));
    }

    @Test
    public void shouldNotLoginCourierWithoutPasswordTest() {
        courierSteps.createCourier(courier);
        Courier noPasswordCourier = new Courier()
                .setPassword(null)
                .setLogin(courier.getLogin());

        courierSteps
                .loginCourier(noPasswordCourier)
                .statusCode(400)
                .body(MESSAGE, Matchers.is(NOT_ENOUGH_DATA_FOR_LOGIN));
    }

    @Test
    public void shouldNotLoginCourierWithIncorrectLoginTest() {
        courierSteps.createCourier(courier);
        Courier incorrectLoginCourier = new Courier()
                .setLogin("12345")
                .setPassword(courier.getPassword());

        courierSteps
                .loginCourier(incorrectLoginCourier)
                .statusCode(404)
                .body(MESSAGE, Matchers.is(ACCOUNT_NOT_FOUND));
    }

    @Test
    public void shouldNotLoginCourierWithIncorrectPasswordTest() {
        courierSteps.createCourier(courier);
        Courier incorrectLoginCourier = new Courier()
                .setPassword("12345")
                .setLogin(courier.getPassword());

        courierSteps
                .loginCourier(incorrectLoginCourier)
                .statusCode(404)
                .body(MESSAGE, Matchers.is(ACCOUNT_NOT_FOUND));
    }

    @Test
    public void shouldNotLoginNonExistentCourierTest() {
        courierSteps.createCourier(courier);
        Courier nonExistentCourier = new Courier()
                .setLogin("12345678")
                .setPassword("123456");
        courierSteps
                .loginCourier(nonExistentCourier)
                .statusCode(404)
                .body(MESSAGE, Matchers.is(ACCOUNT_NOT_FOUND));
    }

    @After
    public void tearDown() {
        Integer id = courierSteps.loginCourier(courier)
                .extract().body().path(ID);
        courier.setId(id);
        courierSteps.deleteCourier(courier);
    }
}
