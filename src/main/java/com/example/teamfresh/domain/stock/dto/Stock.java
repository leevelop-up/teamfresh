package com.example.teamfresh.domain.stock.dto;

import com.example.teamfresh.common.dto.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "stocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Stock extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    private Long productId;

    @Column(nullable = false)
    private int quantity;

    public void decreaseStock(int quantity) {
        if (this.quantity < quantity) {
            throw new RuntimeException("재고가 부족합니다.");
        }
        this.quantity -= quantity;
    }

}
