package com.example.teamfresh.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductRegisterParam {
    private Long productId;
    private String name;
    private int inventory;
    private long price;

    public Product toEntity() {
        return Product.builder()
                .productId(productId)
                .name(name)
                .inventory(inventory)
                .price(price)
                .build();
    }

}
