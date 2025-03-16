package com.example.teamfresh.domain.product.dto;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductRegisterRequest {
    private Long productId;
    private String name;
    private int inventory;
    private long price;

    public ProductRegisterParam toParam(){
        return ProductRegisterParam.builder()
                .productId(productId)
                .name(name)
                .inventory(inventory)
                .price(price)
                .build();

    }
}
