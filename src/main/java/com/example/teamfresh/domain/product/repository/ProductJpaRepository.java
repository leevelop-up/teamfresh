package com.example.teamfresh.domain.product.repository;

import com.example.teamfresh.domain.product.dto.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

}
