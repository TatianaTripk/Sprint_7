package ru.praktikum.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.model.Courier;
import ru.praktikum.steps.CourierSteps;

import static ru.praktikum.config.RestConfig.ErrorMessages.LOGIN_ALREADY_USED;
import static ru.praktikum.config.RestConfig.ErrorMessages.NOT_ENOUGH_DATA_FOR_SIGNUP;
import static ru.praktikum.config.RestConfig.Fields.ID;
import static ru.praktikum.config.RestConfig.Fields.MESSAGE;

public class CreateCourierTests extends BaseTest {
    private CourierSteps courierSteps = new CourierSteps();
    private Courier courier;
    private boolean isCourierCreated = false;

    @Before
    public void setUp() {
        courier = new Courier();
        courier
                .setLogin(RandomStringUtils.randomAlphabetic(10))
                .setPassword(RandomStringUtils.randomAlphabetic(10));
        isCourierCreated = false;
    }

    @Test
    public void shouldCreateCourierTest() {
        courierSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", Matchers.is(true));
        isCourierCreated = true;
    }

    @Test
    public void shouldNotCreateCourierWithoutLoginFieldTest() {
        courier.setLogin(null);
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body(MESSAGE, Matchers.is(NOT_ENOUGH_DATA_FOR_SIGNUP));
        isCourierCreated = false;

    }

    @Test
    public void shouldNotCreateCourierWithoutPasswordFieldTest() {
        courier.setPassword(null);
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body(MESSAGE, Matchers.is(NOT_ENOUGH_DATA_FOR_SIGNUP));
        isCourierCreated = false;
    }

    @Test
    public void shouldNotCreateDuplicateCourierTest() {
        courierSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", Matchers.is(true));
        isCourierCreated = true;
        courierSteps
                .createCourier(courier)
                .statusCode(409)
                .body(MESSAGE, Matchers.is(LOGIN_ALREADY_USED));
    }

    @After
    public void tearDown() {
        if (isCourierCreated) {
            Integer id = courierSteps.loginCourier(courier)
                    .extract().body().path(ID);
            courier.setId(id);
            courierSteps.deleteCourier(courier);
        }
    }
}

