package com.codestates.excercise;

import com.querydsl.core.annotations.QueryProjection;

public class OrderItemDto {

    private int orderPrice;
    private int count;
    private String name;

    @QueryProjection
    public OrderItemDto(int orderPrice, int count, String name) {
        this.orderPrice = orderPrice;
        this.count = count;
        this.name = name;
    }
}