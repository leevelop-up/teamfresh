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
    private Long orderId; // 주문 ID (고유값)

    @Column(length = 12, nullable = false)
    private String customerName; // 주문자명 (한글 4글자 제한)

    @Column(nullable = false)
    private String customerAddress; // 주문자 주소

}
