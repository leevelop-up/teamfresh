package com.example.teamfresh.domain.order.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemParam {

    private Long orderId;
    private Long productId;
    private String productName;
    private int quantity;


    public OrderItemParam(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
