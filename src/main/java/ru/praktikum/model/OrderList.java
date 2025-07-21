package ru.praktikum.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderList {
    private Integer courierId;
    private String nearestStation;
    private Integer limit;
    private Integer page;

    public Integer getCourierId() {
        return courierId;
    }

    public OrderList setCourierId(Integer courierId) {
        this.courierId = courierId;
        return this;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public OrderList setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public OrderList setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public OrderList setPage(Integer page) {
        this.page = page;
        return this;
    }
}