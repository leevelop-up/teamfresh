package com.example.teamfresh.domain.stock.service;

import com.example.teamfresh.common.dto.exception.CouponIssueException;
import com.example.teamfresh.domain.product.dto.Product;
import com.example.teamfresh.domain.stock.dto.Stock;
import com.example.teamfresh.domain.stock.dto.StockRegisterParam;
import com.example.teamfresh.domain.stock.repository.StockJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.teamfresh.common.enums.ReturnCode.STOCK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockJpaRepository stockRepository;

    public void registerStock(StockRegisterParam param) {
        Stock stock = param.toEntity();
        getStock(stock.getProductId());
        stockRepository.save(stock);
    }
    public Stock getStock(Long productId) {
        Stock stock = stockRepository.findByProductId(productId);
        if (stock == null) {
            throw new CouponIssueException(STOCK_NOT_FOUND,"재고 정보가 없습니다.");
        }
        return stock;
    }

    public void decreaseStock(Product product, int quantity) {
        Stock stock = stockRepository.findByProductId(product.getProductId());
        stock.decreaseStock(quantity);
    }
}
