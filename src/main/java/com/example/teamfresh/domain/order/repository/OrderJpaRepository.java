package com.example.teamfresh.domain.order.repository;

import com.example.teamfresh.domain.order.dto.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

}
