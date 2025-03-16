package com.example.teamfresh.domain.order.service;

import com.example.teamfresh.domain.order.repository.ExcelOrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ExcelOrderService {
    private final ExcelOrderJpaRepository excelOrderJpaRepository;

    public void registerOrdersFromExcel(MultipartFile file) {

    }
}
