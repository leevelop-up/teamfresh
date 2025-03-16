package com.example.teamfresh.domain.stock.dto;

import com.example.teamfresh.common.dto.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "stocks") // Order-By 예약어로 인한 오류 방지
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Stock extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId; // 재고 ID

    private Long productId; // 상품 코드

    @Column(nullable = false)
    private int quantity; // 현재 재고 수량

    public void decreaseStock(int quantity) {
        if (this.quantity < quantity) {
            throw new RuntimeException("재고가 부족합니다.");
        }
        this.quantity -= quantity;
    }

    public void increaseStock(int quantity) {
        this.quantity += quantity;
    }

}
