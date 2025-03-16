package com.example.teamfresh.domain.order.dto;

import com.example.teamfresh.common.dto.BaseTimeEntity;
import com.example.teamfresh.domain.product.dto.Product;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Table(name = "order_items") // Order-By 예약어로 인한 오류 방지
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OrderItems extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문 상세 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // 주문 ID

    private Long productId; // 상품 ID

    @Column(length = 100, nullable = false)
    private String productName; // 상품명 (최대 100자)

    @Column(nullable = false)
    private int quantity; // 주문 수량

    public OrderItems(Order order, Product product, int quantity) {
        this.order = order;
        this.productId = product.getProductId();
        this.productName = product.getName();
        this.quantity = quantity;
    }
}
