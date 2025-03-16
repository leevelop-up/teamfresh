package com.example.teamfresh.domain.stock.dto;

import lombok.Builder;

@Builder
public class StockRegisterParam {
    private Long productId;
    private int quantity;

    public Stock toEntity() {
        return Stock.builder()
                .productId(productId)
                .quantity(quantity)
                .build();
    }
    public StockRegisterParam(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
