package ru.praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.model.Courier;

import static io.restassured.RestAssured.given;
import static ru.praktikum.config.RestConfig.*;


public class CourierSteps {

    @Step("Create courier")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .body(courier)
                .when()
                .post(COURIER_CREATE_HANDLER)
                .then();
    }

    @Step("Login courier")
    public ValidatableResponse loginCourier(Courier courier) {
        return given()
                .body(courier)
                .when()
                .post(COURIER_LOGIN_HANDLER)
                .then();
    }

    @Step("Delete courier")
    public ValidatableResponse deleteCourier(Courier courier) {
        return given()
                .pathParams("id", courier.getId())
                .when()
                .delete(COURIER_DELETE_HANDLER)
                .then();
    }
}
