package com.example.teamfresh.domain.order.repository;

import com.example.teamfresh.domain.order.dto.ExcelOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelOrderJpaRepository extends JpaRepository<ExcelOrder, Integer> {
}
