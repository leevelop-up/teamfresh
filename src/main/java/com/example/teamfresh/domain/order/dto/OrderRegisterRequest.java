package com.example.teamfresh.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRegisterRequest {
    private Long orderId;
    private String customerName;
    private String customerAddress;
    private List<OrderItemParam> orderItems = new ArrayList<>();

    public OrderRegisterParam toParam() {
        return OrderRegisterParam.builder()
                .orderId(orderId)
                .customerName(customerName)
                .customerAddress(customerAddress)
                .orderItems(orderItems)
                .build();

    }
}
