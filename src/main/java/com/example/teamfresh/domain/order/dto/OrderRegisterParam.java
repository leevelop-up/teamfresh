package com.example.teamfresh.domain.order.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRegisterParam {

    private Long orderId;
    private String customerName;

    private String customerAddress;

    private List<OrderItemParam> orderItems = new ArrayList<>();


    public Order toEntity() {
        return Order.builder()
                .orderId(orderId)
                .customerName(customerName)
                .customerAddress(customerAddress)
                .build();
    }

    public String toString() {
        return "order{" +
                ", orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", orderItems=" + orderItemsToString();
    }

    private String orderItemsToString() {
        if (orderItems == null || orderItems.isEmpty()) {
            return "[]";
        }
        return orderItems.stream()
                .map(item -> "{productId=" + item.getProductId() +
                        ", productName='" + item.getProductName()+ '\'' +
                        ", quantity=" + item.getQuantity() + "}")
                .collect(Collectors.joining(", ", "[", "]"));
    }


}
