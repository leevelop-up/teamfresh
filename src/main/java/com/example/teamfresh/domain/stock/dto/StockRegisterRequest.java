package com.example.teamfresh.domain.stock.dto;

import jakarta.persistence.Column;

public class StockRegisterRequest {
    private Long productId;
    private int quantity;

    public StockRegisterParam toParam() {
        return StockRegisterParam.builder()
                .productId(productId)
                .quantity(quantity)
                .build();
    }
}
