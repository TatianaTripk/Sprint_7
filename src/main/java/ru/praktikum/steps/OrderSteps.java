package ru.praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.config.RestConfig;
import ru.praktikum.model.Order;
import ru.praktikum.model.OrderList;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Create Order")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .body(order)
                .when()
                .post(RestConfig.ORDER_HANDLER)
                .then();
    }

    @Step("Get order list with parameters")
    public ValidatableResponse getOrderList(OrderList orderList) {
        return given()
                .queryParam("courierId", orderList.getCourierId() != null ? orderList.getCourierId() : null)
                .queryParam("nearestStation", orderList.getNearestStation() != null ? orderList.getNearestStation() : null)
                .queryParam("limit", orderList.getLimit() != null ? orderList.getLimit() : null)
                .queryParam("page", orderList.getPage() != null ? orderList.getPage() : null)
                .when()
                .get(RestConfig.ORDER_HANDLER)
                .then();
    }

    @Step("Cancel order by track number")
    public ValidatableResponse cancelOrder(int trackId) {
        return given()
                .body(Map.of("track", trackId))
                .when()
                .put(RestConfig.ORDER_CANCEL_HANDLER)
                .then();
    }
}
