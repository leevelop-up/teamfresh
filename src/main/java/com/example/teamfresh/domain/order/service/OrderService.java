package com.example.teamfresh.domain.order.service;

import com.example.teamfresh.common.component.DistributeLockExecutor;
import com.example.teamfresh.common.dto.exception.CouponIssueException;
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

import static com.example.teamfresh.common.enums.ReturnCode.INSUFFICIENT_STOCK;
import static com.example.teamfresh.common.enums.ReturnCode.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderJpaRepository orderJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final StockJpaRepository stockJpaRepository;
    private final OrderItemsJpaRepository orderItemJpaRepository;
    private final DistributeLockExecutor distributeLockExecutor;
    private final StockService stockService;

    @Transactional
    public void registerOrder(OrderRegisterParam param) {
        distributeLockExecutor.execute("order_lock", 10000, 10000, () -> {
            validateStock(param);
            Order order = param.toEntity();
            orderJpaRepository.save(order);
            List<OrderItems> orderItems = createOrderItems(order, param.getOrderItems());
            orderItemJpaRepository.saveAll(orderItems);
        });
    }
    private void validateStock(OrderRegisterParam param) {
        for (OrderItemParam itemParam : param.getOrderItems()) {
            Product product = productJpaRepository.findById(itemParam.getProductId())
                    .orElseThrow(() -> new CouponIssueException(PRODUCT_NOT_FOUND, "상품을 찾을 수 없습니다."));
            Integer stockQuantity = stockJpaRepository.findByProductId(product.getProductId()).getQuantity();
            if (stockQuantity < itemParam.getQuantity()) {
                throw new CouponIssueException(INSUFFICIENT_STOCK,"재고가 부족하여 주문이 실패하였습니다. 부족한 상품: " + product.getName());
            }
        }
    }
    private List<OrderItems> createOrderItems(Order order, List<OrderItemParam> orderItemParams) {
        List<OrderItems> orderItems = new ArrayList<>();
        for (OrderItemParam itemParam : orderItemParams) {
            Product product = productJpaRepository.findById(itemParam.getProductId())
                    .orElseThrow(() -> new CouponIssueException(PRODUCT_NOT_FOUND,"상품을 찾을 수 없습니다."));
            stockService.decreaseStock(product, itemParam.getQuantity());
            orderItems.add(new OrderItems(order, product, itemParam.getQuantity()));
        }
        return orderItems;
    }
}
