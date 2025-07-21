package ru.praktikum.tests;


import org.junit.Test;
import ru.praktikum.model.OrderList;
import ru.praktikum.steps.OrderSteps;

import static org.hamcrest.Matchers.notNullValue;

public class OrderListTests extends BaseTest {
    private OrderSteps orderSteps = new OrderSteps();

    @Test
    public void shouldDisplayOrderListTest() {
        OrderList orderListParams = new OrderList();
        orderSteps.getOrderList(orderListParams)
                .statusCode(200)
                .body("orders", notNullValue());
    }
}