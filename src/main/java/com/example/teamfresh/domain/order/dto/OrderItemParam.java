package com.example.teamfresh.domain.order.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemParam {

    private Long orderId; // 주문 ID
    private Long productId; // 상품 ID
    private String productName; // 상품명 (최대 100자)
    private int quantity; // 주문 수량


}
