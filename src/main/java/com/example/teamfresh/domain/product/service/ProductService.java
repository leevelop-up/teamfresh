package com.example.teamfresh.domain.product.service;

import com.example.teamfresh.domain.product.dto.Product;
import com.example.teamfresh.domain.product.dto.ProductRegisterParam;
import com.example.teamfresh.domain.product.repository.ProductJpaRepository;
import com.example.teamfresh.domain.stock.dto.StockRegisterParam;
import com.example.teamfresh.domain.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductJpaRepository productJpaRepository;
    private final StockService stockService;
    public void registerProduct(ProductRegisterParam param) {
        Product product = param.toEntity();
        productJpaRepository.save(product);

        StockRegisterParam stockParam = new StockRegisterParam(product.getProductId(), param.getInventory());
        stockService.registerStock(stockParam);
    }
}
