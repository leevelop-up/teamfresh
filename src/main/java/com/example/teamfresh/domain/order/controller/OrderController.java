package com.example.teamfresh.domain.order.controller;

import com.example.teamfresh.common.dto.response.ApiResponse;
import com.example.teamfresh.common.enums.ReturnCode;
import com.example.teamfresh.domain.order.dto.OrderRegisterParam;
import com.example.teamfresh.domain.order.dto.OrderRegisterRequest;
import com.example.teamfresh.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public ApiResponse<?> register(@RequestBody OrderRegisterRequest orderRegisterRequest) {
        OrderRegisterParam param = orderRegisterRequest.toParam();
        orderService.registerOrder(param);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }


}
