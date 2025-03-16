package com.example.teamfresh.domain.order.controller;

import com.example.teamfresh.common.dto.response.ApiResponse;
import com.example.teamfresh.common.enums.ReturnCode;
import com.example.teamfresh.domain.order.service.ExcelOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderExcelController {
    private final ExcelOrderService excelOrderService;

    @PostMapping("/upload")
    public ApiResponse<?> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ApiResponse.of(ReturnCode.EMPTY_FILE);
        }
        excelOrderService.registerOrdersFromExcel(file);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }
}
