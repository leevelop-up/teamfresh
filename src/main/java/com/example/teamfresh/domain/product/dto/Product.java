package com.example.teamfresh.domain.product.dto;


import com.example.teamfresh.common.dto.BaseTimeEntity;
import com.example.teamfresh.domain.stock.dto.Stock;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "product") // Order-By 예약어로 인한 오류 방지
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; // 상품 코드
    private String name; //상품명
    private int inventory; //재고
    private long price; //기본 상품 금액
}
