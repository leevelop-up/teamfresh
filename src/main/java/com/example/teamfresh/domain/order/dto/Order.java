package com.example.teamfresh.domain.order.dto;


import com.example.teamfresh.common.dto.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(length = 12, nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerAddress;
}
