package com.example.teamfresh.domain.order.service;

import com.example.teamfresh.domain.order.dto.Order;
import com.example.teamfresh.domain.order.dto.OrderItemParam;
import com.example.teamfresh.domain.order.dto.OrderItems;
import com.example.teamfresh.domain.order.dto.OrderRegisterParam;
import com.example.teamfresh.domain.order.repository.OrderItemsJpaRepository;
import com.example.teamfresh.domain.order.repository.OrderJpaRepository;
import com.example.teamfresh.domain.product.dto.Product;
import com.example.teamfresh.domain.product.repository.ProductJpaRepository;
import com.example.teamfresh.domain.stock.dto.Stock;
import com.example.teamfresh.domain.stock.repository.StockJpaRepository;
import com.example.teamfresh.domain.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderJpaRepository orderJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final StockJpaRepository stockJpaRepository;
    private final OrderItemsJpaRepository orderItemJpaRepository;
    private final StockService stockService;

    @Transactional
    public void registerOrder(OrderRegisterParam param) {
        System.out.println(param.getOrderItems());
        // 1. 모든 상품의 재고를 먼저 체크 (부족한 상품이 발견되면 즉시 예외 발생)
        for (OrderItemParam itemParam : param.getOrderItems()) {
            Product product = productJpaRepository.findById(itemParam.getProductId())
                    .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
            Integer stockQuantity = stockJpaRepository.findByProductId(product.getProductId()).getQuantity();
            if (stockQuantity < itemParam.getQuantity()) {
                throw new RuntimeException("재고가 부족하여 주문이 실패하였습니다. 부족한 상품: " + product.getName());
            }
        }

        Order order = param.toEntity();
        orderJpaRepository.save(order);
        List<OrderItems> orderItems = new ArrayList<>();

        // 3. 주문 아이템 생성 및 재고 차감
        for (OrderItemParam itemParam : param.getOrderItems()) {
            Product product = productJpaRepository.findById(itemParam.getProductId()).orElseThrow();

            stockService.decreaseStock(product, itemParam.getQuantity()); // ✅ 재고 차감

            // ✅ DTO → 엔티티 변환 (`OrderItemParam` → `OrderItem`)
            OrderItems orderItem = new OrderItems(order, product, itemParam.getQuantity());
            orderItems.add(orderItem);
        }

        // 4. 주문 상세 저장
        orderItemJpaRepository.saveAll(orderItems);
    }
}
