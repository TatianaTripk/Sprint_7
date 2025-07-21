package ru.praktikum.tests;

import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.model.Order;
import ru.praktikum.steps.OrderSteps;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.praktikum.config.RestConfig.Colours.BLACK;
import static ru.praktikum.config.RestConfig.Colours.GREY;

@RunWith(Parameterized.class)
public class CreateOrderTests extends BaseTest {
    private final OrderSteps orderSteps = new OrderSteps();
    private final List<String> colours;

    public CreateOrderTests(String[] colours) {
        this.colours = Arrays.asList(colours);
    }

    @Parameterized.Parameters()
    public static Object[][] getColourData() {
        return new Object[][]{
                {new String[]{BLACK}},
                {new String[]{GREY}},
                {new String[]{BLACK, GREY}},
                {new String[]{}}
        };
    }

    @Test
    public void createOrderWithDifferentColorsTest() {
        Order order = new Order()
                .setFirstName("Nikolay")
                .setLastName("Nikolayev")
                .setAddress("Petrova, 142")
                .setMetroStation(4)
                .setPhone("+71235556677")
                .setRentTime(5)
                .setDeliveryDate("2025-08-06")
                .setComment("Fast and Furious")
                .setColours(colours);

        ValidatableResponse response = orderSteps.createOrder(order);
        response
                .statusCode(201)
                .body("track", Matchers.is(notNullValue()));
    }
}