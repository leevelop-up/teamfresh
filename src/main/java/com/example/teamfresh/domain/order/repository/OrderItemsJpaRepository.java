package com.example.teamfresh.domain.order.repository;

import com.example.teamfresh.domain.order.dto.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsJpaRepository extends JpaRepository<OrderItems, Integer> {
}
