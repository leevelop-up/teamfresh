package com.example.teamfresh.domain.stock.repository;

import com.example.teamfresh.domain.product.dto.Product;
import com.example.teamfresh.domain.stock.dto.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockJpaRepository extends JpaRepository<Stock, Long> {
    Stock findByProductId(Long productId);
}
