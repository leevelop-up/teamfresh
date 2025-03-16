package com.example.teamfresh.domain.order.dto;

import com.example.teamfresh.common.dto.BaseTimeEntity;
import com.example.teamfresh.domain.product.dto.Product;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OrderItems extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private Long productId;

    @Column(length = 100, nullable = false)
    private String productName;

    @Column(nullable = false)
    private int quantity;

    public OrderItems(Order order, Product product, int quantity) {
        this.order = order;
        this.productId = product.getProductId();
        this.productName = product.getName();
        this.quantity = quantity;
    }
}
