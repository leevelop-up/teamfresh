package com.example.teamfresh.domain.stock.controller;

import com.example.teamfresh.common.dto.response.ApiResponse;
import com.example.teamfresh.common.enums.ReturnCode;
import com.example.teamfresh.domain.stock.dto.StockRegisterParam;
import com.example.teamfresh.domain.stock.dto.StockRegisterRequest;
import com.example.teamfresh.domain.stock.service.StockService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class StockController {
    private final StockService stockService;
    @PostMapping("/stocks")
    public ApiResponse<?> registerStock(StockRegisterRequest stockRegisterRequest) {
        StockRegisterParam param = stockRegisterRequest.toParam();
        stockService.registerStock(param);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }
}
