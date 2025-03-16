package com.example.teamfresh.domain.order.service;

import com.example.teamfresh.TestConfig;
import com.example.teamfresh.domain.order.dto.OrderItemParam;
import com.example.teamfresh.domain.order.dto.OrderRegisterParam;
import com.example.teamfresh.domain.order.repository.OrderItemsJpaRepository;
import com.example.teamfresh.domain.order.repository.OrderJpaRepository;
import com.example.teamfresh.domain.product.dto.Product;
import com.example.teamfresh.domain.product.repository.ProductJpaRepository;
import com.example.teamfresh.domain.stock.dto.Stock;
import com.example.teamfresh.domain.stock.repository.StockJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Transactional
@SpringBootTest
class OrderServiceTest extends TestConfig {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductJpaRepository productRepository;

    @Autowired
    private StockJpaRepository stockRepository;

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Autowired
    private OrderItemsJpaRepository orderItemJpaRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @BeforeEach
    void clean(){
        productRepository.deleteAllInBatch();
        stockRepository.deleteAllInBatch();
    }
    @Test
    @DisplayName("정상_주문_등록_테스트")
    public void success_order() {
        Product product = new Product(null, "테스트 상품", 10, 1000L);
        Product savedProduct = productRepository.save(product);

        Stock stock = new Stock(null, savedProduct.getProductId(), 10);
        stockRepository.save(stock);

        entityManager.flush();

        OrderRegisterParam param = OrderRegisterParam.builder()
                .marketOrderNo(1234L)
                .customerName("홍길동")
                .customerAddress("서울시 강남구")
                .orderItems(List.of(new OrderItemParam(savedProduct.getProductId(), 2)))
                .build();

        orderService.registerOrder(param);

        Stock updatedStock = stockRepository.findByProductId(savedProduct.getProductId());
        if (updatedStock == null) {
            throw new RuntimeException("재고 정보를 찾을 수 없습니다.");
        }

        if (updatedStock.getQuantity() < savedProduct.getInventory()) {
            System.out.println("감소 재고"+updatedStock.getQuantity());
        }
    }

    @Test
    @DisplayName("재고 부족 실페 테스트")
    public void fail_stock() {
        Product product = new Product(null, "테스트 상품", 10, 1000L);
        Product savedProduct = productRepository.save(product);

        Stock stock = new Stock(null, savedProduct.getProductId(), 10);
        stockRepository.save(stock);

        entityManager.flush();

        OrderRegisterParam param = OrderRegisterParam.builder()
                .marketOrderNo(1234L)
                .customerName("홍길동")
                .customerAddress("서울시 강남구")
                .orderItems(List.of(new OrderItemParam(savedProduct.getProductId(), 100)))
                .build();

        orderService.registerOrder(param);

        Stock updatedStock = stockRepository.findByProductId(savedProduct.getProductId());
        if (updatedStock == null) {
            throw new RuntimeException("재고 정보를 찾을 수 없습니다.");
        }
    }
}