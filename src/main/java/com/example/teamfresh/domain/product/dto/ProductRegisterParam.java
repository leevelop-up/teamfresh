package com.example.teamfresh.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductRegisterParam {
    private Long productId; // 상품 코드
    private String name; //상품명
    private int inventory; //재고
    private long price; //기본 상품 금액

    public Product toEntity() {
        return Product.builder()
                .productId(productId)
                .name(name)
                .inventory(inventory)
                .price(price)
                .build();
    }

}
